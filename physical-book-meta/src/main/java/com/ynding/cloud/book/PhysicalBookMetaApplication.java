package com.ynding.cloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhysicalBookMetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalBookMetaApplication.class, args);
    }

}
