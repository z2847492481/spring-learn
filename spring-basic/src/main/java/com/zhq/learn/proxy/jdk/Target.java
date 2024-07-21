package com.zhq.learn.proxy.jdk;

/**
 * @author zhq123
 * @date 2024-07-21
 */
public class Target implements Foo{
    @Override
    public void foo() {
        System.out.println("target foo...");
    }

    @Override
    public int bar() {
        System.out.println("target bar...");
        return 0;
    }
}
