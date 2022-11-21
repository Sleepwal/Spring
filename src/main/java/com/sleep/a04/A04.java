package com.sleep.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @package: com.sleep.a04
 * @className: A04
 * @author: SleepWalker
 * @description: bean后处理器作用
 * @date: 21:56
 * @version: 1.0
 */
public class A04 {
    public static void main(String[] args) {
        //GenericApplicationContext是一个干净的容器，没有bean后处理器
        GenericApplicationContext context = new GenericApplicationContext();

        //注册Bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);

        //添加bean后处理器
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class); //@Autowired @Value

        context.registerBean(CommonAnnotationBeanPostProcessor.class); //@Resource @PostConstruct @PreDestroy

        //初始化容器
        context.refresh();  //执行BeanFactory后处理器，添加bean后处理器，初始化所有单例

        //销毁容器
        context.close();

    }
}
