package com.ynding.cloud.core.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ynding
 */
@EnableEurekaServer
@SpringBootApplication
public class CoreServerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServerEurekaApplication.class, args);
    }

}
