package customspring.classaccesor.impl;

import application.exception.NoDataFoundException;
import customspring.classaccesor.ClassesAccessor;
import customspring.metadata.Component;
import customspring.metadata.Repository;
import customspring.metadata.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClassesAccessorImpl implements ClassesAccessor {

    public List<Class> findAllClasses(File directory, String packageName) {
        List<Class> allClasses = new ArrayList<>();
        try {
            if (!directory.exists()) {
                throw new NoDataFoundException("Такого файла не существует");
            }
            File[] files = directory.listFiles();
            for (File file : Objects.requireNonNull(files)) {
                if (file.isDirectory()) {
                    allClasses.addAll(findAllClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".java")) {
                    allClasses.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 5)));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allClasses;
    }

    public List<Class> findAllAnnotatedClasses(List<Class> classes) {
        List<Class> annotatedClasses = new ArrayList<>();
        for (Class<?> clazz : classes) {
            if (clazz.getAnnotation(Component.class) != null
                    || clazz.getAnnotation(Service.class) != null
                    || clazz.getAnnotation(Repository.class) != null) {
                annotatedClasses.add(clazz);
            }
        }
        return annotatedClasses;
    }
}
