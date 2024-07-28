package com.zhq.learn.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author zhq123
 * @date 2024-07-22
 */
@Aspect
public class MyAspect {


    @Before("execution(* com.zhq.learn.aop.MyService.save())")
    public void before() {
        System.out.println("before");
    }

    @Before("execution(* com.zhq.learn.aop.MyService.update())")
    public void before2() {
        System.out.println("before2");
    }
}
