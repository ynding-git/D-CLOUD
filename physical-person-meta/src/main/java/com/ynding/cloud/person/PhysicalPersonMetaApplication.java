package com.ynding.cloud.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@ServletComponentScan
@EnableBinding(Sink.class)//绑定消息代理
public class PhysicalPersonMetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalPersonMetaApplication.class, args);
    }

}
