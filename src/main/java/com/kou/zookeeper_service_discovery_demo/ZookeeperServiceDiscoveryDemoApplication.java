package com.kou.zookeeper_service_discovery_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kou.zookeeper_service_discovery_demo.mapper")
public class ZookeeperServiceDiscoveryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServiceDiscoveryDemoApplication.class, args);
    }

}