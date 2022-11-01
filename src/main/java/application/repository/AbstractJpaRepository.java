package application.repository;

import java.util.List;
import java.util.Optional;
public interface AbstractJpaRepository<T,ID> {

    void setClazz(Class<T> clazzToSet);

    Optional<T> findById(ID id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(ID id);
}
