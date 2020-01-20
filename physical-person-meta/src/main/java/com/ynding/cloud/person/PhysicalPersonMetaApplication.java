package com.ynding.cloud.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableMongoAuditing
//@EnableMongoRepositories(basePackages="com.ynding.cloud.person.data")
public class PhysicalPersonMetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalPersonMetaApplication.class, args);
    }

}
