package com.debuggeandoideas.notification_system;

import com.debuggeandoideas.notification_system.models.NotificationEvent;
import com.debuggeandoideas.notification_system.models.NotificationStatus;
import com.debuggeandoideas.notification_system.models.Priority;
import com.debuggeandoideas.notification_system.service.EmailService;
import com.debuggeandoideas.notification_system.service.NotificationService;
import com.debuggeandoideas.notification_system.service.PhoneService;
import com.debuggeandoideas.notification_system.service.TeamsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class NotificationSystem {

    private final Sinks.Many<NotificationEvent> mainEventSink;
    @Getter
    private final Sinks.Many<NotificationEvent> historySink;

    private final NotificationService teamsService;
    private final NotificationService emailService;
    private final NotificationService phoneService;

    private final Sinks.One<NotificationEvent> teamsSink;
    private final Sinks.One<NotificationEvent> emailSink;
    private final Sinks.One<NotificationEvent> phoneSink;

    private final ConcurrentMap<String, NotificationEvent> notificationCache;

    public NotificationSystem() {

        this.mainEventSink = Sinks.many().multicast().onBackpressureBuffer();
        this.historySink = Sinks.many().replay().limit(50);

        this.teamsSink = Sinks.one();
        this.emailSink = Sinks.one();
        this.phoneSink = Sinks.one();

        this.phoneService = new PhoneService();
        this.teamsService = new TeamsService();
        this.emailService = new EmailService();

        this.notificationCache = new ConcurrentHashMap<>();

        this.setupProcessingFlows();
    }

    public NotificationSystem(
            NotificationService teamsService,
            NotificationService emailService,
            NotificationService phoneService) {
        this.mainEventSink = Sinks.many().multicast().onBackpressureBuffer();
        this.historySink = Sinks.many().replay().limit(50);

        this.teamsSink = Sinks.one();
        this.emailSink = Sinks.one();
        this.phoneSink = Sinks.one();

        this.teamsService = teamsService;
        this.emailService = emailService;
        this.phoneService = phoneService;

        this.notificationCache = new ConcurrentHashMap<>();

        setupProcessingFlows();
    }

    private void setupProcessingFlows() {
        this.mainEventSink
                .asFlux()
                .doOnNext(event -> log.info("Received new event: {}", event))
                .doOnNext(this::updateEventStatus)
                .doOnNext(this.historySink::tryEmitNext)
                .subscribe(this::routeEventByPriority);

        this.setupTeamsProcessor();
        this.setupEmailProcessor();
        this.setupPhoneProcessor();
    }

    private void setupTeamsProcessor() {
        this.teamsSink
                .asMono()
                .flatMap(event ->
                        this.teamsService.sendNotification(event)
                                .subscribeOn(Schedulers.boundedElastic())
                                .doOnSuccess(success -> this.updateSuccess(event, TEAMS_CHANNEL))
                                .doOnError(error -> this.updateErrorStatus(event, TEAMS_CHANNEL, error))
                                .onErrorResume(error -> Mono.just(false))
                )
                .subscribe();
    }

    private void setupEmailProcessor() {
        this.emailSink.asMono()
                .flatMap(event ->
                        this.emailService.sendNotification(event)
                                .subscribeOn(Schedulers.boundedElastic())
                                .doOnSuccess(success -> this.updateSuccess(event, EMAIL_CHANNEL))
                                .doOnError(error -> updateErrorStatus(event, EMAIL_CHANNEL, error))
                                .onErrorResume(e -> Mono.just(false))
                )
                .subscribe();
    }

    private void setupPhoneProcessor() {
        phoneSink.asMono()
                .flatMap(event ->
                        this.phoneService.sendNotification(event)
                                .subscribeOn(Schedulers.boundedElastic())
                                .doOnSuccess(success -> this.updateSuccess(event, PHONE_CHANNEL))
                                .doOnError(error -> updateErrorStatus(event, PHONE_CHANNEL, error))
                                .retry(3)
                                .onErrorResume(e -> Mono.just(false))

                )
                .subscribe();
    }


    private void updateEventStatus(NotificationEvent event) {
        if (Objects.isNull(event.getStatus())) {
            event.setId(UUID.randomUUID().toString());
        }

        if (Objects.isNull(event.getStatus())) {
            event.setStatus(NotificationStatus.PENDING);
        }

        this.notificationCache.put(event.getId(), event);
    }

    private void updateSuccess(NotificationEvent event, String channel) {
        log.info("Success event by: {}, event: {}", channel, event.getId());
        NotificationEvent cacheEvent = this.notificationCache.get(event.getId());

        if (Objects.nonNull(cacheEvent)) {
            cacheEvent.setStatus(NotificationStatus.DELIVERED);
            this.historySink.tryEmitNext(cacheEvent);
        }

    }

    private void updateErrorStatus(NotificationEvent event, String channel, Throwable error) {
        log.error("Error to send notification by: {}, for event: {}, error: {}", channel, event.getId(), error);
        NotificationEvent cacheEvent = this.notificationCache.get(event.getId());

        if (Objects.nonNull(cacheEvent)) {
            cacheEvent.setStatus(NotificationStatus.FAILED);
            this.historySink.tryEmitNext(cacheEvent);
        }
    }

    private void routeEventByPriority(NotificationEvent event) {
        this.teamsSink.tryEmitValue(event);

        if (event.getPriority() == Priority.HIGH || event.getPriority() == Priority.MEDIUM) {
            this.emailSink.tryEmitValue(event);
        }

        if (event.getPriority() == Priority.HIGH) {
            this.phoneSink.tryEmitValue(event);
        }
    }

    public void publishEvent(NotificationEvent event) {
        this.mainEventSink.tryEmitNext(event);
    }

    public Flux<NotificationEvent> getNotificationHistory() {
        return this.historySink.asFlux();
    }

    public Mono<NotificationEvent> getNotificationById(String id) {
        return Mono.justOrEmpty(this.notificationCache.get(id));
    }

    public Flux<NotificationEvent> retryFailedNotification() {
        return Flux.fromIterable(this.notificationCache.values())
                .filter(event -> event.getStatus() == NotificationStatus.FAILED)
                .doOnNext(this::publishEvent);
    }

    private static final String TEAMS_CHANNEL = "Teams";
    private static final String EMAIL_CHANNEL = "Email";
    private static final String PHONE_CHANNEL = "Phone";


}
