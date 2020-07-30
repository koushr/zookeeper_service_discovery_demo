package com.kou.zookeeper_service_discovery_demo.configuration;

import com.kou.zookeeper_service_discovery_demo.config.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisPoolConfiguration {
    @Bean
    public JedisPool JedisPoolFactory(RedisConfig redisConfig) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        return new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort());
    }
}