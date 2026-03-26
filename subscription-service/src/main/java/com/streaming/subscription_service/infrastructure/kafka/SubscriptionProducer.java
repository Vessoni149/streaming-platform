package com.streaming.subscription_service.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionProducer {
    private final KafkaTemplate<String, SubscriptionEvent> kafkaTemplate;

    public SubscriptionProducer(KafkaTemplate<String, SubscriptionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatusUpdate(Long userId, String status) {
        SubscriptionEvent event = new SubscriptionEvent(userId, status);
        kafkaTemplate.send("subscription-topic", userId.toString(), event);
    }
}

