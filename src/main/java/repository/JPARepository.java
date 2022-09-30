package repository;

import java.util.List;
import java.util.Optional;

public interface JPARepository<T> {
    void save(T t);

    void delete(Long id);

    void update(T t);

    Optional<T> findById(Long id);

    List<T> findAll();
}
