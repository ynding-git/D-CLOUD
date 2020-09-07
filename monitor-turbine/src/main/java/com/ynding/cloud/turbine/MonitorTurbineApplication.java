package com.ynding.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author ynding
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class MonitorTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorTurbineApplication.class, args);
    }

}
