package com.kou.zookeeper_service_discovery_demo.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    List<Map> getAll();

}