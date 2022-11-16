package com.sleep.a01;

import org.springframework.context.ApplicationEvent;

/**
 * @package: com.sleep.a01
 * @className: MyUserRegisteredEvent
 * @author: SleepWalker
 * @description: TODO
 * @date: 16:12
 * @version: 1.0
 */
public class MyUserRegisteredEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyUserRegisteredEvent(Object source) {
        super(source);
    }
}
