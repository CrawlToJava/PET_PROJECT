package repository.impl;

import entity.Orders;
import exceptions.NoSuchElementException;
import repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersRepositoryImpl implements OrdersRepository {
    private final List<Orders> orders = new ArrayList<>();

    @Override
    public void save(Orders orders) {
        if (isPresent(this.orders, orders.getId())) {
            throw new RuntimeException("Человек с таким id уже есть");
        } else {
            this.orders.add(orders);
        }
    }

    @Override
    public void delete(Long id) {
        orders.remove(Math.toIntExact(id));
    }

    @Override
    public int size() {
        return orders.size();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orders.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Long id, Orders orders) {
        if (isPresent(this.orders, id)) {
            Orders updatedOrder = this.orders.get(Math.toIntExact(id));
            updatedOrder.setOrdersStatus(orders.getOrdersStatus());
            updatedOrder.setOrderedAt(orders.getOrderedAt());
            updatedOrder.setFinishedAt(orders.getFinishedAt());
            updatedOrder.setScooters(orders.getScooters());
            updatedOrder.setUsers(orders.getUsers());
            updatedOrder.setRentalPoints(orders.getRentalPoints());
            updatedOrder.setTotalPrice(orders.getTotalPrice());
        } else {
            throw new NoSuchElementException("Заказ не найден");
        }
    }

    public boolean isPresent(List<Orders> ordersList, Long id) {
        return ordersList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<Orders> findAll() {
        return orders;
    }
}
