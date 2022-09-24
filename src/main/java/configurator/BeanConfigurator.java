package configurator;

public interface BeanConfigurator {
    <T> Class<? extends T> getImplementation(Class<T> interfaceClass);
}
