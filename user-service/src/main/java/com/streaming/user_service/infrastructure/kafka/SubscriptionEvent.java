package com.streaming.user_service.infrastructure.kafka;

public record SubscriptionEvent(Long userId, String status) {
}
