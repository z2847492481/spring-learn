package com.zhq.learn.proxy.jdk;

import java.lang.reflect.Method;

/**
 * @author zhq123
 * @date 2024-07-21
 */
public interface InvocationHandler {

    /**
     *
     * @param method 方法对象
     * @param args 方法参数
     */
    Object invoke(Method method, Object[] args) throws Throwable;
}
