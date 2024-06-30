package com.zhq.learn.factory2;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhq123
 * @date 2024-06-23
 */
public class MyApplication {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class); // 处理@Autowired @Value
        context.registerBean(CommonAnnotationBeanPostProcessor.class); // 处理@Resource @PostConstruct @PreDestroy
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory()); // 处理@ConfigurationProperties

        context.refresh();

        System.out.println(context.getBean(Bean4.class));

        context.close();
    }
}
