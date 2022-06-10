package com.para.tranzai.command.core;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.TypeUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * 对被{@link CommandProcessor}注解标记的类进行处理并解析。
 * @author ankol
 */
@Slf4j
@Component
public class CommandProcessorHandler implements BeanPostProcessor {

    @SuppressWarnings("rawtypes")
    private final Predicate[] predicates = new Predicate[]{
            o -> ClassUtil.isAssignable(MessageEvent.class, o.getClass()),
            o -> ClassUtil.isAssignable(String[].class, o.getClass())
    };

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        boolean hasAnnotation = AnnotationUtil.hasAnnotation(bean.getClass(), CommandProcessor.class);
        if (hasAnnotation) {
            CommandProcessor annotation = AnnotationUtil.getAnnotation(bean.getClass(), CommandProcessor.class);
            try {
                checkArgument(annotation, bean, beanName);
                registerCommandProcessor(annotation, bean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }

    private void registerCommandProcessor(CommandProcessor annotation, Object bean) {

    }

    @SuppressWarnings("unchecked")
    private void checkArgument(CommandProcessor processor, Object bean, String beanName) throws Exception {
        if (ClassUtil.isAssignable(BiConsumer.class, bean.getClass())) {
            Type[] typeArguments = TypeUtil.getTypeArguments(bean.getClass());
            for (int i = 0; i < typeArguments.length; i++) {
                if (!predicates[i].test(typeArguments[i])) {
                    throw new RuntimeException();
                }
            }
        } else {
            log.error("Resolve 'CommandProcessor' Bean [{}] Error. It need implement 'BiConsumer' interface.", beanName);
        }
    }

}
