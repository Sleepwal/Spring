package com.sleep.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @package: com.sleep.a01
 * @className: TestComponent4
 * @author: SleepWalker
 * @description: TODO
 * @date: 10:49
 * @version: 1.0
 */
@Component
public class TestComponent4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestComponent4.class);

    @Autowired
    ApplicationEventPublisher publisher;

    public void register() {
        LOGGER.debug("用户注册");
        //发布事件，事件源是自己
        publisher.publishEvent(new MyUserRegisteredEvent(this));
    }
}
