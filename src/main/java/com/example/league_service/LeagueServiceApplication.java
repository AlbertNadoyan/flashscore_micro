package com.example.league_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
//@ComponentScan(basePackages = {"com.example.sport_service.*"})
//@EntityScan(basePackages = "com.example.sport_service.*")
//@EnableJpaRepositories(basePackages = "com.example.sport_service.*")
public class LeagueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueServiceApplication.class, args);
    }

}
