package com.zhq.learn.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author zhq123
 * @date 2024-07-21
 */
@Component
public class A {

    @Lazy
    @Autowired
    private B1 b1;

    @Autowired
    private B2 b2;

    @Autowired
    private ObjectFactory<B3> b3;

    @Autowired
    private ApplicationContext applicationContext;

    public B1 getB1() {
        return b1;
    }

    public B2 getB2() {
        return b2;
    }

    public B3 getB3() {
        return b3.getObject();
    }

    public B4 getB4() {
        return applicationContext.getBean(B4.class);
    }
}
