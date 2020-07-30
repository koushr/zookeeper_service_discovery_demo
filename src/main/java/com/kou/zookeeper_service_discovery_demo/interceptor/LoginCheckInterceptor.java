package com.kou.zookeeper_service_discovery_demo.interceptor;

import com.google.common.collect.ImmutableMap;
import com.kou.zookeeper_service_discovery_demo.util.JedisUtils;
import com.kou.zookeeper_service_discovery_demo.util.ObjectMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Access-Token, Origin, X-Requested-With, Content-Type, Accept, Test, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        // 如果是登录接口的话，放行，让人登录去
        if ("/login".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        }


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String uid = null;
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    uid = cookie.getValue();
                }
            }
            // 判断是否有登录信息
            if (StringUtils.isNotBlank(uid)) {
                String user = jedisUtils.get(uid);
                if (StringUtils.isNotBlank(user)) {
                    request.setAttribute("user", user);
                    return true;
                }
            }
        }
        response.getWriter().write(ObjectMapperUtils.toJSON(ImmutableMap.of("code", "unLogin")));
        return false;
    }

}