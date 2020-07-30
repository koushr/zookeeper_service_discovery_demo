package com.kou.zookeeper_service_discovery_demo.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy
@Component
public class LoginCheckAdvice {

    @Before(value = "execution(* com.kou.zookeeper_service_discovery_demo.controller.HelloController.*(..))")
    public void rejectUnLogin(JoinPoint joinPoint) {
        System.out.println("joinPoint= " + joinPoint);
    }

}