package com.sleep.a03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @package: com.sleep.a03
 * @className: A03
 * @author: SleepWalker
 * @description: TODO
 * @date: 19:07
 * @version: 1.0
 */
@SpringBootApplication
public class A03 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A03.class, args);
        context.close();
    }
}
