package configurator.impl;

import configurator.BeanConfigurator;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class BeanConfiguratorImpl implements BeanConfigurator {
    final Reflections scanner;

    private final Map<Class, Class> interfaceToImplementation;

    public BeanConfiguratorImpl(String packageToScan, Map<Class, Class> interfaceToImplementation) {
        this.scanner = new Reflections(packageToScan);
        this.interfaceToImplementation = new ConcurrentHashMap<>(interfaceToImplementation);
    }

    @Override
    public <T> Class<? extends T> getImplementation(Class<T> interfaceClass) {
        return interfaceToImplementation.computeIfAbsent(interfaceClass, clazz -> {
            Set<Class<? extends T>> implementationClasses = scanner.getSubTypesOf(interfaceClass);
            if (implementationClasses.size() != 1) {
                throw new RuntimeException("Интферфейс имеет больше чем одну реализацию");
            }
            return implementationClasses.stream().findFirst().get();
        });
    }
}
