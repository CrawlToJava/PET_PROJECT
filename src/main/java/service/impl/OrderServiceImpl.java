package service.impl;

import entity.*;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
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
        Order order = orderRepository.findById(ordersId).orElseThrow(() -> new NoDataFoundException("Заказ с таким id не найден"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoDataFoundException("Пользователь с таким id не найден"));
        RentalPoint rentalPoint = rentalPointRepository.findById(rentalPointsId).orElseThrow(() -> new NoDataFoundException("Точка проката с таким id не найден"));
        Valid.isRentAvailable(scooter.getScooterStatus(), user.getUserStatus(), rentalPoint.getRentalPointsStatus());
        scooter.setScooterStatus(ScooterStatus.BOOKED);
        order.setOrderStatus(OrderStatus.OPEN);
        order.setOrderedAt(LocalDateTime.now());
    }

    @Override
    public void finishRent(Long scootersId, Long ordersId) {
        Scooter scooter = scooterRepository.findById(scootersId).orElseThrow(() -> new NoDataFoundException("Электросамокат с таким id не найден"));
        Order order = orderRepository.findById(ordersId).orElseThrow(() -> new NoDataFoundException("Заказ с таким id не найден"));
        Valid.isFinishRentAvailable(scooter);
        scooter.setScooterStatus(ScooterStatus.AVAILABLE);
        order.setOrderStatus(OrderStatus.CLOSE);
        order.setFinishedAt(LocalDateTime.now());
        order.setTotalPrice(countOrderPrice(order.getOrderedAt(),
                order.getFinishedAt(),
                scooter.getPrice()));
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
    public void update(Long id, Order order) {
        orderRepository.update(id, order);
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
