package com.zhq.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener1 {

    @EventListener
    public void onApplicationEvent(MyEvent event)
    {
        log.info("event:{}", event);
    }
}
