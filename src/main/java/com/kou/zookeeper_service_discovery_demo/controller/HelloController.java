package com.kou.zookeeper_service_discovery_demo.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/getAll")
    public Map getAll() {
        return ImmutableMap.of("result", Arrays.asList(1, 2, 3));

    }
}
