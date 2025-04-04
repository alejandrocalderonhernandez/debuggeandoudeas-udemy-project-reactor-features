package com.debuggeandoideas.notification_system.service;


import com.debuggeandoideas.notification_system.models.NotificationEvent;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<Boolean> sendNotification(NotificationEvent event);
}
