package com.debuggeandoideas.notification_system.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {

    private String id;
    private String source;
    private String message;
    private Priority priority;
    private LocalDateTime timestamp;
    private NotificationStatus status;
}
