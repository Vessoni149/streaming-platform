package com.streaming.user_service.infrastructure.kafka;

import com.streaming.user_service.model.SubscriptionStatus;
import com.streaming.user_service.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionConsumer {
    @Autowired
    private IUserRepo userRepository;

    @KafkaListener(topics = "subscription-topic")
    public void consume(SubscriptionEvent event) {
        System.out.println("¡MENSAJE RECIBIDO! -> Usuario: " + event.userId());
        userRepository.findById(event.userId()).ifPresent(user -> {
            // Convertimos el String que viene de Kafka al Enum de tu entidad User
            user.setSubscriptionStatus(SubscriptionStatus.valueOf(event.status()));
            userRepository.save(user);
            System.out.println("Estado actualizado para el usuario: " + event.userId());
        });
    }
}
