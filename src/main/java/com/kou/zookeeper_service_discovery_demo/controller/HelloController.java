package com.kou.zookeeper_service_discovery_demo.controller;

import com.google.common.collect.ImmutableMap;
import com.kou.zookeeper_service_discovery_demo.config.DbConfig;
import com.kou.zookeeper_service_discovery_demo.mapper.UserMapper;
import com.kou.zookeeper_service_discovery_demo.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private DbConfig dbConfig;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAll")
    public Map getAll() {
        List<Map> list = userMapper.getAll();
        System.out.println("list= " + ObjectMapperUtils.toJSON(list));
        System.out.println("dbConfig= " + ObjectMapperUtils.toJSON(dbConfig));
        return ImmutableMap.of("result", Arrays.asList(1, 2, 3));
    }
}
