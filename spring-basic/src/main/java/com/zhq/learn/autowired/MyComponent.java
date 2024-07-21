package com.zhq.learn.autowired;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhq123
 * @date 2024-07-21
 */
@Data
@Slf4j
public class MyComponent implements BeanNameAware, InitializingBean {
    @Override
    public void setBeanName(String name) {
        log.info("beanName:{}", name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }
}
