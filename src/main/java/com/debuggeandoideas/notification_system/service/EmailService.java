package com.debuggeandoideas.notification_system.service;

import com.debuggeandoideas.notification_system.models.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class EmailService implements NotificationService{

    public Mono<Boolean> sendNotification(NotificationEvent event) {

        return Mono.fromCallable(() -> {

            Thread.sleep(300);


            //simulate error with 15% probability
            if (ThreadLocalRandom.current().nextInt(100) < 15) {
                throw new RuntimeException("Error on send msg in Email");
            }

            log.info("Msg in Email OK: {}", event);

            return true;

        });
    }
}
