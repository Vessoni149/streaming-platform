package com.streaming.user_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadBalancerController {

    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public String test() {
        String port = environment.getProperty("local.server.port");
        System.out.println("--> Petición atendida en el puerto: " + port);
        return "Respuesta desde la instancia en el puerto: " + port;
    }

}
