package com.sleep.a03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @package: com.sleep.a03
 * @className: LifeCycleBean
 * @author: SleepWalker
 * @description: 生命周期
 * @date: 19:07
 * @version: 1.0
 */
@Component
public class LifeCycleBean {
    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        log.debug("构造方法");
    }

    @Autowired
    public void aVoid(@Value("${JAVA_HOME}") String home) {
        log.debug("依赖注入java_home: {}", home);
    }

    @PostConstruct
    public void init() {
        log.debug("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.debug("销毁");
    }
}
