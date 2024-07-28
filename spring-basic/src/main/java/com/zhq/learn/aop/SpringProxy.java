package com.zhq.learn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author zhq123
 * @date 2024-07-28
 */
public class SpringProxy {
    public static void main(String[] args) {

        // 1、切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* save())");

        // 2、准备通知
        MethodInterceptor interceptor = invocation -> {
            System.out.println("before");
            Object result = invocation.proceed();
            System.out.println("after");
            return result;
        };

        // 3、组装切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, interceptor);

        // 4、创建代理
        MyServiceImpl target = new MyServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        MyService proxy = (MyService) proxyFactory.getProxy();
        proxy.save();
        proxy.update();

        /**
         * 关于Spring代理的选择
         * 1. 如果proxyTargetClass=false, 则要判断是否有接口, 如果有接口, 则使用jdk动态代理, 否则使用cglib动态代理
         * 2. 如果proxyTargetClass=true, 则使用cglib动态代理
         */
    }

    interface MyService {
        void save();

        void update();
    }

    static class MyServiceImpl implements MyService {
        @Override
        public void save() {
            System.out.println("save");
        }

        @Override
        public void update() {
            System.out.println("update");
        }
    }
}
