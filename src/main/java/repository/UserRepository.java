package repository;

import entity.User;

import java.util.List;

public interface UserRepository extends JPARepository<User>{
    public List<User> findByLastName(String lastName);
}
