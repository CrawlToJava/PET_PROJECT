package factory;

import annotation.Inject;
import configurator.BeanConfigurator;
import configurator.impl.BeanConfiguratorImpl;
import config.Configuration;
import config.impl.ConfigurationImpl;
import context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BeanFactory {

    private final BeanConfigurator beanConfigurator;

    private final ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        Configuration configuration = new ConfigurationImpl();
        this.beanConfigurator = new BeanConfiguratorImpl(configuration.getPackageToScan(), configuration.getInterfaceToImplementation());
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementation(implementationClass);
        }
        try {
            T bean = implementationClass.getDeclaredConstructor().newInstance();
            for (Field field : Arrays.stream(implementationClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
                field.setAccessible(true);
                field.set(bean,applicationContext.getBean(field.getType()));
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
