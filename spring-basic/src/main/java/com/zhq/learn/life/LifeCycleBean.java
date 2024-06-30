package com.zhq.learn.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zhq123
 * @date 2024-06-23
 */
@Component
public class LifeCycleBean {

    // 日志
    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        log.info("construct...");
    }

    @Autowired
    public void attributeAutowired(@Value("${JAVA_HOME}") String javaHome) {
        log.info("java home:{}", javaHome);
    }

    @PostConstruct
    public void init() {
        log.info("初始化方法");
    }

    @PreDestroy
    public void destroy() {
        log.info("销毁方法");
    }
}
