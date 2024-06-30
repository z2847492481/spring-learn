package com.zhq.learn.processor.factory;

import com.zhq.learn.processor.factory.bean.Bean3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhq123
 * @date 2024-06-30
 */
@ComponentScan("com.zhq.learn.processor.factory.bean")
@Configuration
public class Config {

    @Bean(initMethod = "init")
    public Bean3 bean3() {
        return new Bean3();
    }
}
