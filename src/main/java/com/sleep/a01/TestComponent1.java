package com.sleep.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @package: com.sleep.a01
 * @className: TestComponent1
 * @author: SleepWalker
 * @description: TODO
 * @date: 10:49
 * @version: 1.0
 */
@Component
public class TestComponent1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestComponent1.class);

    @EventListener
    public void listen(MyUserRegisteredEvent userRegisteredEvent) {
        LOGGER.debug("发送短信");
    }
}
