package com.zhq.learn.processor.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * 自定义解析@Bean注解
 * @author zhq123
 * @date 2024-06-30
 */
public class MyBeanAnnotationPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        try {
            MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(new ClassPathResource("com/zhq/learn/processor/factory/Config.class"));
            // 拿到类上的所有标注了@Bean的方法
            Set<MethodMetadata> annotatedMethods = metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
            for (MethodMetadata annotatedMethod : annotatedMethods) {
                // 构建BeanDefinition
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
                // 设置工厂方法，并且这个方法是在config这个工厂bean里面的
                beanDefinitionBuilder.setFactoryMethodOnBean(annotatedMethod.getMethodName(), "config");
                // 设置方法中参数的注入方式
                beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
                // 判断@Bean是否有initMethod属性
                String initMethod = annotatedMethod.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
                // 设置初始化方法
                if (StringUtils.hasText(initMethod)) {
                    beanDefinitionBuilder.setInitMethodName(initMethod);
                }
                // 给容器中注入BeanDefinition
                AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                if (beanFactory instanceof DefaultListableBeanFactory) {
                    ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(annotatedMethod.getMethodName(), beanDefinition);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
