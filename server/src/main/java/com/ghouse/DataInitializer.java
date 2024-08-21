package com.ghouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ghouse.service.UserService;

@Component
@Configuration
public class DataInitializer {

    @Autowired
    UserService userService;

    @Bean
    ApplicationRunner initializer() {
        return args -> userService.initializeUsers();
    }
}
