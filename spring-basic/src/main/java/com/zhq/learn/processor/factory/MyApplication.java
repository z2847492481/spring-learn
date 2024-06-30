package com.zhq.learn.processor.factory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhq123
 * @date 2024-06-30
 */
public class MyApplication {

    private static final Logger log = LoggerFactory.getLogger(MyApplication.class);
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);
        //context.registerBean(MyComponentScanPostProcessor.class);
        context.registerBean(MyBeanAnnotationPostProcessor.class);
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            log.info("beanDefinitionName:{}", beanDefinitionName);
        }
        context.close();
    }
}
