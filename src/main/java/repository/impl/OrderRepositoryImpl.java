package repository.impl;

import entity.Order;
import exceptions.NoSuchElementException;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        if (isPresent(this.orders, order.getId())) {
            throw new RuntimeException("Человек с таким id уже есть");
        } else {
            this.orders.add(order);
        }
    }

    @Override
    public void delete(Long id) {
        orders.remove(Math.toIntExact(id));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Long id, Order order) {
        if (isPresent(this.orders, id)) {
            Order updatedOrder = this.orders.get(Math.toIntExact(id));
            updatedOrder.setOrderStatus(order.getOrderStatus());
            updatedOrder.setOrderedAt(order.getOrderedAt());
            updatedOrder.setFinishedAt(order.getFinishedAt());
            updatedOrder.setScooter(order.getScooter());
            updatedOrder.setUser(order.getUser());
            updatedOrder.setRentalPoint(order.getRentalPoint());
            updatedOrder.setTotalPrice(order.getTotalPrice());
        } else {
            throw new NoSuchElementException("Заказ не найден");
        }
    }

    public boolean isPresent(List<Order> orderList, Long id) {
        return orderList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<Order> findAll() {
        return orders;
    }
}
