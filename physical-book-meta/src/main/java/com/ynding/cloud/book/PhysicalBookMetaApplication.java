package com.ynding.cloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.ynding.cloud.common.model.entity")
@EnableBinding(Source.class)//绑定消息代理
public class PhysicalBookMetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalBookMetaApplication.class, args);
    }
}
