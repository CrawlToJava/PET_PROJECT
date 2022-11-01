package application.test;

import application.repository.ModelRepository;
import customspring.beanfactory.BeanFactory;
import customspring.beanfactory.impl.BeanFactoryImpl;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactoryImpl();
        beanFactory.instantiate(new File("D:\\Repository\\src\\main\\java\\application"), "application");
        beanFactory.injectDependency();
        System.out.println(beanFactory.getSingletons());
        ModelRepository modelRepository = (ModelRepository) beanFactory.getBean("ModelRepositoryImpl");
        System.out.println(modelRepository);
    }
}
