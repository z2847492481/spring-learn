package com.zhq.learn.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhq123
 * @date 2024-07-22
 */
@Configuration
@EnableAspectJAutoProxy
public class MyConfiguration {


    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
}
