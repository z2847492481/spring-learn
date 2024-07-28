package com.zhq.learn.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhq123
 * @date 2024-07-22
 */
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        MyService myService = context.getBean(MyService.class);
        MyService.save();
        myService.save();
        myService.update();
    }
}
