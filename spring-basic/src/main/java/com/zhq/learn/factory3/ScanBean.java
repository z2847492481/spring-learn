package com.zhq.learn.factory3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhq123
 * @date 2024-06-30
 */
@Component
public class ScanBean {
    private static final Logger log = LoggerFactory.getLogger(ScanBean.class);

    public ScanBean() {
        log.info("扫描到了scanBean");
    }
}
