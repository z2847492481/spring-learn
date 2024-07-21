package com.zhq.learn.proxy.jdk;

import java.lang.reflect.Method;

/**
 * @author zhq123
 * @date 2024-07-21
 */
public class $Proxy0 implements Foo{

    private static Method foo;

    private static Method bar;

    private InvocationHandler h;


    static {
        try {
            foo = Foo.class.getMethod("foo");
            bar = Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public $Proxy0(InvocationHandler h) {
        this.h = h;
    }

    @Override
    public void foo() {
        try {
            h.invoke(foo, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int bar() {
        try {
            return (int) h.invoke(bar, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


}
