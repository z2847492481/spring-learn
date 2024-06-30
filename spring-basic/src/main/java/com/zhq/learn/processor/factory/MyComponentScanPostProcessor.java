package com.zhq.learn.processor.factory;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhq123
 * @date 2024-06-30
 */
public class MyComponentScanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 拿到目前所有扫描到的一些bean，然后去读这些类上是否有@ComponentScan注解
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (componentScan == null){
            return;
        }
        DefaultListableBeanFactory defaultListableBeanFactory;
        if (beanFactory instanceof DefaultListableBeanFactory) {
            defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        }else {
            throw new RuntimeException("beanFactory is not DefaultListableBeanFactory");
        }
        String[] basePackages = componentScan.basePackages();
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
        for (String basePackage : basePackages) {
            String scanPackage = "classpath*:" +  basePackage.replace(".", "/") + "/**/*.class";
            try {
                Resource[] resources = resourcePatternResolver.getResources(scanPackage);
                // 读取每一个类的元信息
                for (Resource resource : resources) {
                    MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    // 判断这个类上是否标识了@Component注解或衍生注解
                    if (annotationMetadata.hasMetaAnnotation(Component.class.getName()) || annotationMetadata.hasAnnotation(Component.class.getName())) {
                        // 有的话就生成一个bd注册到容器里面
                        AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(metadataReader.getClassMetadata().getClassName()).getBeanDefinition();
                        defaultListableBeanFactory.registerBeanDefinition(beanNameGenerator.generateBeanName(bd, defaultListableBeanFactory), bd);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
