package com.zhq.learn.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhq123
 * @date 2024-07-21
 */
@Scope(scopeName = "prototype")
@Component
public class B3 {
}
