package com.sleep.a02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package:        com.sleep.a01
 * @className:      TestBeanFactory
 * @author:     SleepWalker
 * @description:  BeanFactory和Bean后处理器
 * @date:   21:36
 * @version:    1.0
 */ 
public class TestBeanFactory {
    public static void main(String[] args) {
        //BeanFactory中的最重要的实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //此时还没有Bean，添加Bean的定义（class，scope，构造方法，销毁方法），BeanFactory可以根据这个来创建Bean
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
                .setScope("singleton").getBeanDefinition();
        //将beanDefinition注册到BeanFactory中
        beanFactory.registerBeanDefinition("config", beanDefinition);


        Config bean = beanFactory.getBean(Config.class);
        System.out.println("bean = " + bean);

        //给BeanFactory添加后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //拿到BeanFactory中添加的处理器, 给BeanFactory添加功能
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        //将BeanFactory中的BeanDefinition查看一下
        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println("name = " + name);
        }

        //Bean后处理器，针对 Bean 的生命周期的各个阶段提供扩展，@Autowired @Resource
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream().forEach(beanFactory::addBeanPostProcessor);

        //取消懒加载
        beanFactory.preInstantiateSingletons();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //获取Bean2中的Bean1
        System.out.println(beanFactory.getBean(Bean2.class).getBean1());

        /*
            学到了什么:
            a. beanFactory 不会做的事
                   1. 不会主动调用 BeanFactory 后处理器
                   2. 不会主动添加 Bean 后处理器
                   3. 不会主动初始化单例
                   4. 不会解析beanFactory 还不会解析 ${ } 与 #{ }
            b. bean 后处理器会有排序的逻辑
         */

    }


    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("创建Bean1类对象");
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("创建Bean2类对象");
        }

        @Autowired
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }
    }
}
