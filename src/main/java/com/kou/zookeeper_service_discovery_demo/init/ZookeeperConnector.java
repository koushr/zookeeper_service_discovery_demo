package com.kou.zookeeper_service_discovery_demo.init;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@EnableConfigurationProperties
public class ZookeeperConnector implements CommandLineRunner {
    @Value("${server.weight}")
    private int weight;

    @Value("${server.port}")
    private int port;

    @Value("${zookeeper.url}")
    private String zookeeperUrl;

    @Override
    public void run(String... args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperUrl, 5000, 3000, retryPolicy);
        client.start();
        String ip = "127.0.0.1";
        String hostAndPort = ip + ":" + port;
        String path = "/server1/" + hostAndPort;
        try {
            if (client.checkExists().forPath(path) != null) {
                client.delete().forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path, String.valueOf(weight).getBytes(StandardCharsets.UTF_8));
    }
}
