package com.zhq.learn.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhq123
 * @date 2024-07-21
 */
@ComponentScan("com.zhq.learn.scope")
@Slf4j
public class ScopeApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeApplication.class);
        A a1 = context.getBean(A.class);
        log.info("b1:{}", a1.getB1());
        log.info("b1:{}", a1.getB1());
        log.info("b2:{}", a1.getB2());
        log.info("b2:{}", a1.getB2());
        log.info("b3:{}", a1.getB3());
        log.info("b3:{}", a1.getB3());
        log.info("b4:{}", a1.getB4());
        log.info("b4:{}", a1.getB4());
    }
}
