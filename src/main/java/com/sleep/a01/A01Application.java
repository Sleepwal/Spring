package com.sleep.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * @package: com.sleep.a01
 * @className: A01Application
 * @author: SleepWalker
 * @description: TODO BeanFactory 和 Application 的区别
 * @date: 22:48
 * @version: 1.0
 */
@SpringBootApplication
public class A01Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(A01Application.class);

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException{

        ConfigurableApplicationContext context = SpringApplication.run(A01Application.class);

         /*
            1. 到底什么是 BeanFactory
                - 它是 ApplicationContext 的父接口
                - 它才是 Spring 的核心容器, 主要的 ApplicationContext 实现都【组合】了它的功能
         */
        /*context.getBean("aaaa");*/
        //System.out.println("context = " + context);

        /*
            2. BeanFactory 能干点啥
                - 表面上只有 getBean
                - 实际上控制反转、基本的依赖注入、直至 Bean 的生命周期的各种功能, 都由它的实现类提供
         */
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);   //允许访问私有对象
        //反射调用，singletonObjects的get方法需要一个对象
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //拿出BeanFactory对象
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        //找到以testComponent开头的Bean
        map.entrySet().stream().filter(e -> e.getKey().startsWith("testComponent"))
                .forEach(e -> {
                    System.out.println(e.getKey() + " = " + e.getValue());
                });
        /*map.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey() + " = " + e.getValue());
                });*/



    }
}
