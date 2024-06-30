package com.zhq.learn.factory;

import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 介绍@Configuration和@Bean的解析，是BeanFactory被增强后具有的功能
 */
public class MyFactoryApplication {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        AbstractBeanDefinition configBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MyConfig01.class).setScope("singleton").getBeanDefinition();

        defaultListableBeanFactory.registerBeanDefinition("myConfig", configBeanDefinition);

        // 给BeanFactory中注册一些注解相关的BeanFactoryPostProcessor
        AnnotationConfigUtils.registerAnnotationConfigProcessors(defaultListableBeanFactory);

        // 获取到每一个BeanFactoryPostProcessor并执行
        defaultListableBeanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach( beanFactoryPostProcessor -> {
           beanFactoryPostProcessor.postProcessBeanFactory(defaultListableBeanFactory);
        });

        // 添加一些BeanPostProcessor, 完成依赖注入的功能, 例如@Autowired、@Resource
        defaultListableBeanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(defaultListableBeanFactory::addBeanPostProcessor);

        /**
         * myConfig
         * @Configuration 和 @Bean 注解的解析由下面的 BeanFactoryPostProcessor 实现
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * bean01
         * bean02
         */
        for (String definitionName : defaultListableBeanFactory.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }

        // 默认情况下, beanFactory是懒加载的, 懒加载就是不会立即创建bean的, 只有当bean被使用时才会创建
        // 可以通过preInstantiateSingletons()方法来触发加载所有的单例bean
        //defaultListableBeanFactory.preInstantiateSingletons();

        System.out.println(defaultListableBeanFactory.getBean(Bean01.class).getBean02());
    }

    @Configuration
    static class MyConfig01 {
        @Bean
        public Bean01 bean01() {
            return new Bean01();
        }

        @Bean
        public Bean02 bean02() {
            return new Bean02();
        }
    }

    static class Bean01 {
        private static final Logger log = org.slf4j.LoggerFactory.getLogger(Bean01.class);

        @Autowired
        private Bean02 bean02;

        public Bean01() {
            log.info("Bean01....构造函数...");
        }

        public Bean02 getBean02() {
            return bean02;
        }
    }

    static class Bean02 {
        private static final Logger log = org.slf4j.LoggerFactory.getLogger(Bean02.class);

        public Bean02() {
            log.info("Bean02....构造函数...");
        }
    }
}
