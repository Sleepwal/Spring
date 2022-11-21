package com.sleep.a03;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: com.sleep.a03
 * @className: TestMethodTemplate
 * @author: SleepWalker
 * @description: 模板方法
 * @date: 20:07
 * @version: 1.0
 */
public class TestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> System.out.println("----------解析@Autowired注解：" + bean));
        beanFactory.addBeanPostProcessor(bean -> System.out.println("----------解析@Resource注解：" + bean));
        Object bean = beanFactory.getBean();
    }

    static interface BeanPostProcessor {
        public void inject(Object bean);
    }
    static class MyBeanFactory {
        private List<BeanPostProcessor> list = new ArrayList<>();
        public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
            list.add(beanPostProcessor);
        }

        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造 " + bean);
            System.out.println("依赖注入 " + bean);
            //调用解析注解方法
            for (BeanPostProcessor beanPostProcessor : list) {
                beanPostProcessor.inject(bean);
            }

            System.out.println("初始化 " + bean);
            System.out.println("销毁 " + bean);

            return bean;
        }
    }
}
