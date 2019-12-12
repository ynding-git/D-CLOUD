package com.ynding.cloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.ynding.cloud.common.model.entity")
public class PhysicalBookMetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalBookMetaApplication.class, args);
    }
}
