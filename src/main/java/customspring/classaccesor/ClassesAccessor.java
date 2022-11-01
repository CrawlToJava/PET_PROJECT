package customspring.classaccesor;

import java.io.File;
import java.util.List;

public interface ClassesAccessor {
    List<Class> findAllClasses(File directory, String packageName);

    List<Class> findAllAnnotatedClasses(List<Class> classes);
}
