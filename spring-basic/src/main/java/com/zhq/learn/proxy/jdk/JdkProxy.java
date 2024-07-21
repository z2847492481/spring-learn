package com.zhq.learn.proxy.jdk;

/**
 * @author zhq123
 * @date 2024-07-21
 */
public class JdkProxy {

    public static void main(String[] args) {
        Foo target = new Target();

        $Proxy0 proxy = new $Proxy0((method, args1) -> {
            System.out.println("before...");
            return method.invoke(target, args1);
        });

        proxy.foo();

        proxy.bar();
    }
}
