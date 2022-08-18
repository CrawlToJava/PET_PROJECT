package repository;

import entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    void save(Orders orders);

    void delete(Long id);

    int size();

    void update(Long id, Orders orders);

    Optional<Orders> findById(Long id);

    List<Orders> findAll();
}
