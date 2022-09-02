package repository.impl;

import entity.Order;
import repository.OrderRepository;
import valid.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        Valid.isOrderPresent(orders, order.getId());
        orders.add(order);
    }

    @Override
    public void delete(Long id) {
        orders.remove(Math.toIntExact(id));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public void update(Long id, Order order) {
        Valid.isOrderPresent(orders, id);
        Order updatedOrder = orders.get(Math.toIntExact(id));
        updatedOrder.setOrderStatus(order.getOrderStatus());
        updatedOrder.setOrderedAt(order.getOrderedAt());
        updatedOrder.setFinishedAt(order.getFinishedAt());
        updatedOrder.setScooter(order.getScooter());
        updatedOrder.setUser(order.getUser());
        updatedOrder.setRentalPoint(order.getRentalPoint());
        updatedOrder.setTotalPrice(order.getTotalPrice());
    }


    public List<Order> findAll() {
        return orders;
    }
}
