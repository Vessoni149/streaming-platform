package com.streaming.subscription_service.infrastructure.kafka;

public record SubscriptionEvent(Long userId, String status) {
}
