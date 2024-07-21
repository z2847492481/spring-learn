package com.zhq.learn.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author zhq123
 * @date 2024-07-21
 */
@Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class B2 {
}
