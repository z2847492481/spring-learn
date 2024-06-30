package com.zhq.learn.factory3;


import com.zhq.learn.factory2.Bean1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 介绍ConfigurationClassPostProcessor
 * @author zhq123
 * @date 2024-06-30
 */

public class MyFactoryApplication {

    private static final Logger log = LoggerFactory.getLogger(MyFactoryApplication.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class); // 处理@Resource @PostConstruct @PreDestroy
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            log.info("beanDefinitionName:{}", beanDefinitionName);
        }
        context.close();
    }
}
