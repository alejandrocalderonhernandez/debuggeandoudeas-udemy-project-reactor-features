package com.debuggeandoideas.notification_system.service;

import com.debuggeandoideas.notification_system.models.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class PhoneService implements NotificationService{

    public Mono<Boolean> sendNotification(NotificationEvent event) {

        return Mono.fromCallable(() -> {

            Thread.sleep(1000);


            //simulate error with 20% probability
            if (ThreadLocalRandom.current().nextInt(100) < 20) {
                throw new RuntimeException("Error on send msg in Phone call");
            }

            log.info("Msg in Phone OK: {}", event);

            return true;

        });
    }
}
