package org.paratranz.bot.mirai.command.core;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.TypeUtil;
import org.paratranz.bot.mirai.command.annotation.CommandProcessor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * 对被{@link CommandProcessor}注解标记的类进行处理并解析。
 * @author ankol
 */
@Slf4j
@Component
public class CommandProcessorHandler implements BeanPostProcessor, ApplicationContextAware {

    /**
     * 参数校验器
     */
    private static final List<Predicate<Class<?>>> argumentValidators = List.of(
            type -> ClassUtil.isAssignable(MessageEvent.class, type),
            type -> ClassUtil.isAssignable(String[].class, type)
    );
    private ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("rawtypes")
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        CommandProcessor hasAnnotation = bean.getClass().getAnnotation(CommandProcessor.class);
        if (hasAnnotation != null) {
            CommandProcessor annotation = AnnotationUtil.getAnnotation(bean.getClass(), CommandProcessor.class);
            try {
                //检查指令处理器的参数是否正确
                checkArgument(bean, beanName);
                //注册指令处理器
                CommandManger.registerCommandProcessor(annotation.value(), (AbstractCommandProcessor) bean, false);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                SpringApplication.exit(applicationContext);
            }
        }
        return bean;
    }


    private void checkArgument(Object bean, String beanName) throws RuntimeException {
        if (ClassUtil.isAssignable(BiConsumer.class, bean.getClass())) {
            //验证指令解析器参数是否正确.
            Type[] typeArguments = TypeUtil.getTypeArguments(bean.getClass());
            for (int i = 0; i < typeArguments.length; i++) {
                if (!argumentValidators.get(i).test((Class<?>) typeArguments[i])) {
                    log.error("Bean [{}] Illegal TypeArgument [" + typeArguments[i] + "]", beanName);
                }
            }
        } else {
            log.error("Resolve 'CommandProcessor' bean [{}] error. it must be implement 'BiConsumer' interface.", beanName);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
