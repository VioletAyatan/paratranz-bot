package com.para.tranzai.command.core;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.TypeUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * 对被{@link CommandProcessor}注解标记的类进行处理并解析。
 * @author ankol
 */
@Slf4j
//@Component
public class CommandProcessorHandler implements BeanPostProcessor, ApplicationContextAware {

    /**
     * 参数校验器
     */
    private static final List<Predicate<Class<?>>> argumentValidators = List.of(
            type -> ClassUtil.isAssignable(MessageEvent.class, type),
            type -> ClassUtil.isAssignable(MessageEvent.class, type)
    );
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        boolean hasAnnotation = AnnotationUtil.hasAnnotation(bean.getClass(), CommandProcessor.class);
        if (hasAnnotation) {
            CommandProcessor annotation = AnnotationUtil.getAnnotation(bean.getClass(), CommandProcessor.class);
            try {
                checkArgument(bean, beanName);
                registerCommandProcessor(annotation, bean);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                SpringApplication.exit(applicationContext);
            }
        }
        return bean;
    }

    private void registerCommandProcessor(CommandProcessor annotation, Object bean) {

    }

    private void checkArgument(Object bean, String beanName) throws RuntimeException {
        if (ClassUtil.isAssignable(BiConsumer.class, bean.getClass())) {
            Type[] typeArguments = TypeUtil.getTypeArguments(bean.getClass());
            for (int i = 0; i < typeArguments.length; i++) {
                if (!argumentValidators.get(i).test((Class<?>) typeArguments[i])) {
                    throw new RuntimeException("Illegal TypeArgument [" + typeArguments[i] + "]");
                }
            }
        } else {
            log.error("Resolve 'CommandProcessor' Bean [{}] Error. It need implement 'BiConsumer' interface.", beanName);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
