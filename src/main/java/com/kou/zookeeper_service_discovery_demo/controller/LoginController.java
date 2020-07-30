package com.kou.zookeeper_service_discovery_demo.controller;

import com.google.common.collect.ImmutableMap;
import com.kou.zookeeper_service_discovery_demo.mapper.UserMapper;
import com.kou.zookeeper_service_discovery_demo.util.JedisUtils;
import com.kou.zookeeper_service_discovery_demo.util.ObjectMapperUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisUtils jedisUtils;

    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        userName = userName != null ? userName : "";

        String password = request.getParameter("password");
        password = password != null ? password : "";


        Map<String, Object> map = userMapper.getUser(ImmutableMap.of("userName", userName, "password", password));
        if (MapUtils.isEmpty(map)) {
            return ImmutableMap.of("code", "unregistered");
        }

        map.remove("password");

        String uid = userName + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString()
                + "_" + RandomUtils.nextInt(0, Integer.MAX_VALUE) + "_" + RandomStringUtils.randomAlphanumeric(10);
        jedisUtils.setex(uid, 3600 * 24 * 30, ObjectMapperUtils.toJSON(map));

        Cookie authorizationCookie = new Cookie("Authorization", uid);
        authorizationCookie.setHttpOnly(true);
        authorizationCookie.setMaxAge(3600);
        response.addCookie(authorizationCookie);

        return ImmutableMap.of("code", "success");
    }

}