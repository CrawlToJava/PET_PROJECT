package service;

import entity.Order;
import repository.JPARepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderService extends JPARepository<Order> {

    void startRent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId);

    void finishRent(Long scootersId, Long ordersId);

    BigDecimal countOrderPrice(LocalDateTime orderedAt, LocalDateTime finishedAt, BigDecimal price);
}
