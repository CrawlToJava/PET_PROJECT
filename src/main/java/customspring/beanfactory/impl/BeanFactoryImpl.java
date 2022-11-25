package customspring.beanfactory.impl;

import customspring.beanfactory.BeanFactory;
import customspring.classaccesor.impl.ClassesAccessorImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {
    private final Map<String, Object> singletons = new HashMap<>();

    private final ClassesAccessorImpl classesInPackage = new ClassesAccessorImpl();

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    @Override
    public void injectDependency() {

    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

    public void instantiate(File file, String basePackage) {
        try {
            List<Class> allAnnotatedClasses = classesInPackage
                    .findAllAnnotatedClasses(classesInPackage
                            .findAllClasses(file, basePackage));
            for (Class clazz : allAnnotatedClasses) {
                Object instance = clazz.newInstance();
                String beanName = makeBeanName(clazz);
                singletons.put(beanName, instance);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String makeBeanName(Class clazz) {
        return clazz.getName().substring(clazz.getName().lastIndexOf(".")).replace(".", "");
    }
}



