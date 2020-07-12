package com.kou.zookeeper_service_discovery_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = "db.properties")
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DbConfig {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}