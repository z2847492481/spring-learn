package com.zhq.learn.life;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhq123
 * @date 2024-06-23
 */
//@SpringBootApplication
public class LifeApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LifeApplication.class, args);
        context.close();
    }
}
