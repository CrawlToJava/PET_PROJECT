package repository;

import entity.Users;


import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(Users users);

    void delete(Long id);

    void update(Long id, Users users);

    Optional<Users> findById(Long id);

    List<Users> findAll();

    List<Users> findBySecondName(String secondName);
}
