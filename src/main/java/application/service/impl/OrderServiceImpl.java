package application.service.impl;

import application.entity.*;
import application.exception.NoDataFoundException;
import application.repository.OrderRepository;
import application.repository.RentalPointRepository;
import application.repository.ScooterRepository;
import application.repository.UserRepository;
import application.service.OrderService;
import application.valid.Valid;
import customspring.metadata.Autowired;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScooterRepository scooterRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RentalPointRepository rentalPointRepository;

    @Override
    public void startRent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId) {
        Scooter scooter = scooterRepository.findById(scootersId).orElseThrow(() -> new NoDataFoundException("Электросамокат с таким id не найден"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoDataFoundException("Пользователь с таким id не найден"));
        RentalPoint rentalPoint = rentalPointRepository.findById(rentalPointsId).orElseThrow(() -> new NoDataFoundException("Точка проката с таким id не найден"));
        Valid.isRentAvailable(scooter.getScooterStatus(), user.getUserStatus(), rentalPoint.getRentalPointsStatus());
        Valid.isOrderPresent(ordersId, orderRepository);
        scooterRepository.update(new Scooter(scootersId, scooter.getPrice(), rentalPoint, scooter.getModel(), ScooterStatus.BOOKED, user));
        orderRepository.save(new Order(ordersId, LocalDateTime.now(), LocalDateTime.now(), null, OrderStatus.OPEN, user, scooter, rentalPoint));
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
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Заказа с таким id не существует"));
        orderRepository.delete(id);
    }

    @Override
    public void update(Order order) {
        orderRepository.findById(order.getId()).orElseThrow(() -> new NoDataFoundException("Заказа с таким id не существует"));
        orderRepository.update(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Заказа с таким id не сущестует")));
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            throw new NoDataFoundException("Таблица с заказами пустая");
        }
        return orderList;
    }
}
