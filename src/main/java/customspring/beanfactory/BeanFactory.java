package customspring.beanfactory;

import java.io.File;
import java.util.Map;

public interface BeanFactory {

    Object getBean(String beanName);

    void instantiate(File file, String basePackage);

    Map<String, Object> getSingletons();

    void injectDependency();

}
