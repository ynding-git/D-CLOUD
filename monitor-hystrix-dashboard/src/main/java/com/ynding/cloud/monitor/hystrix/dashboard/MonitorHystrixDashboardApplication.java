package com.ynding.cloud.monitor.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class MonitorHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorHystrixDashboardApplication.class, args);
    }

}
