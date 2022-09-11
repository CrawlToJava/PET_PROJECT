package repository;

import entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    void delete(Long id);

    void update(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();
}
