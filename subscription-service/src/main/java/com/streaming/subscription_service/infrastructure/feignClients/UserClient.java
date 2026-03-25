package com.streaming.subscription_service.infrastructure.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service") // Nombre que tiene el MS en Eureka
public interface UserClient {

    @GetMapping("/users/exists/{id}")
    Boolean checkUserExists(@PathVariable("id") Long id);
}
