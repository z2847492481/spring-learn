package com.zhq.learn.autowired;

import org.springframework.context.support.GenericApplicationContext;

/**
 *
 * 介绍Aware接口和InitializingBean接口
 * @author zhq123
 * @date 2024-07-21
 */
public class MyApplication {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("myComponent", MyComponent.class);

        context.refresh();

        context.close();
    }
}
