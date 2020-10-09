package com.ynding.cloud.core.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ynding
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
public class CoreServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServerConfigApplication.class, args);
    }

}
