package context;

import factory.BeanFactory;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {
    @Setter
    private BeanFactory beanFactory;

    private final Map<Class, Object> beanMap = new ConcurrentHashMap<>();

    public <T> T getBean(Class<T> clazz) {
        if (beanMap.containsKey(clazz)) {
            return (T) beanMap.get(clazz);
        }
        T bean = beanFactory.getBean(clazz);

        beanMap.put(clazz, bean);

        return bean;
    }
}
