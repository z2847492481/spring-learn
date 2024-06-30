package com.zhq.learn.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件发布器ApplicationEventPublisher
 * 事件ApplicationEvent
 * 事件监听器EventListener
 * @author zhq
 * @create 2024/6/22
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
