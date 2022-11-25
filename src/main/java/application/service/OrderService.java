package application.service;

import application.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    void startRent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId);

    void finishRent(Long scootersId, Long ordersId);

    BigDecimal countOrderPrice(LocalDateTime orderedAt, LocalDateTime finishedAt, BigDecimal price);

    void save(Order order);

    void delete(Long id);

    void update(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();
}
