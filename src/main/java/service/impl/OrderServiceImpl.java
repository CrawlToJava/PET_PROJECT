package service.impl;

import entity.*;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.*;
import service.OrderService;
import valid.Valid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final ScooterRepository scooterRepository;
    private final OrderRepository orderRepository;
    private final RentalPointRepository rentalPointRepository;

    @Override
    public void startRent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId) {
        Scooter scooter = scooterRepository.findById(scootersId).orElseThrow(() -> new NoDataFoundException("Электросамокат с таким id не найден"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoDataFoundException("Пользователь с таким id не найден"));
        RentalPoint rentalPoint = rentalPointRepository.findById(rentalPointsId).orElseThrow(() -> new NoDataFoundException("Точка проката с таким id не найден"));
        Valid.isRentAvailable(scooter.getScooterStatus(), user.getUserStatus(), rentalPoint.getRentalPointsStatus());
        Valid.isOrderPresent(ordersId, orderRepository);
        scooterRepository.update(new Scooter(scootersId, scooter.getPrice(), rentalPoint, scooter.getModel(), ScooterStatus.BOOKED, user));
        orderRepository.save(new Order(ordersId, LocalDateTime.now(), LocalDateTime.now(), null, OrderStatus.OPEN, user, scooter, rentalPoint)); //TODO как передать времени завершения null
    }

    @Override
    public void finishRent(Long scooterId, Long orderId) {
        Scooter scooter = scooterRepository.findById(scooterId).orElseThrow(() -> new NoDataFoundException("Электросамокат с таким id не найден"));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoDataFoundException("Заказ с таким id не найден"));
        scooterRepository.update(new Scooter(scooterId, scooter.getPrice(), scooter.getRentalPoint(), scooter.getModel(), ScooterStatus.AVAILABLE, scooter.getUser()));
        orderRepository.update(new Order(orderId, order.getOrderedAt(), LocalDateTime.now(), countOrderPrice(order.getOrderedAt(), LocalDateTime.now(), scooter.getPrice()), OrderStatus.CLOSE, order.getUser(), order.getScooter(), order.getRentalPoint()));
    }

    @Override
    public BigDecimal countOrderPrice(LocalDateTime orderedAt, LocalDateTime finishedAt, BigDecimal price) {
        Long start = orderedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long end = finishedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return price.multiply(BigDecimal.valueOf(end - start));
    }

    @Override
    public void save(Order order) {
        Optional<Order> orderFromDataBase = orderRepository.findById(order.getId());
        if (orderFromDataBase.isEmpty()) {
            orderRepository.save(order);
        } else {
            throw new NotAvailableException("Заказ с таким id уже есть");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Order> orderFromDataBase = orderRepository.findById(id);
        if (orderFromDataBase.isPresent()) {
            orderRepository.delete(id);
        } else {
            throw new NotAvailableException("Заказа с таким id не сущестует");
        }
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
