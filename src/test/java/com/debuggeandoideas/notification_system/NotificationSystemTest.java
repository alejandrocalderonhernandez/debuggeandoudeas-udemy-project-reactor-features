package com.debuggeandoideas.notification_system;


import com.debuggeandoideas.notification_system.models.NotificationEvent;
import com.debuggeandoideas.notification_system.models.NotificationStatus;
import com.debuggeandoideas.notification_system.models.Priority;
import com.debuggeandoideas.notification_system.service.EmailService;
import com.debuggeandoideas.notification_system.service.NotificationService;
import com.debuggeandoideas.notification_system.service.PhoneService;
import com.debuggeandoideas.notification_system.service.TeamsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
public class NotificationSystemTest {

    private NotificationService mockTeamsService;
    private NotificationService mockEmailService;
    private NotificationService mockPhoneService;

    private NotificationSystem notificationSystem;

    private AtomicInteger teamsCallCount;
    private AtomicInteger emailCallCount;
    private AtomicInteger phoneCallCount;


    @BeforeEach
    void setup() {
        this.teamsCallCount = new AtomicInteger(0);
        this.emailCallCount = new AtomicInteger(0);
        this.phoneCallCount = new AtomicInteger(0);

        this.mockTeamsService = mock(TeamsService.class);
        this.mockEmailService = mock(EmailService.class);
        this.mockPhoneService = mock(PhoneService.class);

        when(this.mockTeamsService.sendNotification(any(NotificationEvent.class)))
                .thenAnswer(invocation -> {
                    this.teamsCallCount.incrementAndGet();
                    return Mono.just(true);
        });

        when(this.mockEmailService.sendNotification(any(NotificationEvent.class)))
                .thenAnswer(invocation -> {
                    this.emailCallCount.incrementAndGet();
                    return Mono.just(true);
        });

        when(this.mockPhoneService.sendNotification(any(NotificationEvent.class)))
                .thenAnswer(invocation -> {
                    this.phoneCallCount.incrementAndGet();
                    return Mono.just(true);
        });

        this.notificationSystem = new NotificationSystem(
                mockTeamsService, mockEmailService, mockPhoneService
        );
    }

    @Test
    @DisplayName("Should send events with LOW priority")
    void testLowPriority() {
        NotificationEvent event = this.createTestEvent(Priority.LOW);

        this.notificationSystem.publishEvent(event);

        verify(mockTeamsService, times(1)).sendNotification(any());
        verify(mockEmailService, never()).sendNotification(any());
        verify(mockPhoneService, never()).sendNotification(any());

        assert this.teamsCallCount.get() == 1;
        assert this.emailCallCount.get() == 0;
        assert this.phoneCallCount.get() == 0;

    }

    @Test
    @DisplayName("Should send events with MEDIUM priority")
    void mediumPriorityEventsShouldGoToTeamsAndEmail() {
        NotificationEvent testEvent = createTestEvent(Priority.MEDIUM);

        notificationSystem.publishEvent(testEvent);

        verify(mockTeamsService, times(1)).sendNotification(any());
        verify(mockEmailService, times(1)).sendNotification(any());
        verify(mockPhoneService, never()).sendNotification(any());

        assert teamsCallCount.get() == 1;
        assert emailCallCount.get() == 1;
        assert phoneCallCount.get() == 0;
    }

    @Test
    @DisplayName("Should send events with HIGH priority")
    void highPriorityEventsShouldGoToAllChannels() {
        NotificationEvent testEvent = createTestEvent(Priority.HIGH);

        notificationSystem.publishEvent(testEvent);

        verify(mockTeamsService, times(1)).sendNotification(any());
        verify(mockEmailService, times(1)).sendNotification(any());
        verify(mockPhoneService, times(1)).sendNotification(any());

        assert teamsCallCount.get() == 1;
        assert emailCallCount.get() == 1;
        assert phoneCallCount.get() == 1;
    }

    @Test
    @DisplayName("Should history keep last 3 elements")
    void shouldHistoryKeep3Events() {

        NotificationEvent testEvent1 = createTestEvent(Priority.LOW);
        NotificationEvent testEvent2 = createTestEvent(Priority.MEDIUM);
        NotificationEvent testEvent3 = createTestEvent(Priority.HIGH);

        this.notificationSystem.publishEvent(testEvent1);
        this.notificationSystem.publishEvent(testEvent2);
        this.notificationSystem.publishEvent(testEvent3);

        StepVerifier.create(notificationSystem.getNotificationHistory().take(3))
                .expectNextCount(3)
                .verifyComplete();
    }


    @Test
    @DisplayName("Should change FAILED STATUS on event in history sink")
    void shouldSetFailedStatusOnException() {

        when(this.mockTeamsService.sendNotification(any(NotificationEvent.class)))
                .thenReturn(Mono.error(new RuntimeException("Error on send msg in Teams")));

        NotificationEvent notificationEvent = this.createTestEvent(Priority.LOW);
        this.notificationSystem.publishEvent(notificationEvent);


        verify(this.mockTeamsService, times(1)).sendNotification(any());

        StepVerifier.create(this.notificationSystem.getNotificationHistory().take(1))
                .expectNextMatches(event -> event.getStatus() == NotificationStatus.FAILED)
                .verifyComplete();
    }


    private NotificationEvent createTestEvent(Priority priority) {
        return NotificationEvent.builder()
                .id(UUID.randomUUID().toString())
                .source("TEST")
                .message("Test msg with priority: " + priority.toString())
                .priority(priority)
                .timestamp(LocalDateTime.now())
                .status(NotificationStatus.PENDING)
                .build();
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}