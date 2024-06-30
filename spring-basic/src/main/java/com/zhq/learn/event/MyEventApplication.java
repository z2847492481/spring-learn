package com.zhq.learn.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class MyEventApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyEventApplication.class, args);

        // 发送事件
        context.publishEvent(new MyEvent(context));

    }
}
