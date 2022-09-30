package repository.impl;

import database.DataBase;
import database.Queryses;
import entity.*;
import exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private DataBase dataBase;

    private UserRepository userRepository;

    private ScooterRepository scooterRepository;

    private RentalPointRepository rentalPointRepository;

    @Override
    public void save(Order order) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.SAVE_ORDER);
            saveOrder(order, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.DELETE_ORDER + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.UPDATE_ORDER + order.getId())) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getOrderedAt()));
            statement.setTimestamp(2, Timestamp.valueOf(order.getFinishedAt()));
            statement.setBigDecimal(3, order.getTotalPrice());
            statement.setString(4, String.valueOf(order.getOrderStatus()).toUpperCase());
            statement.setLong(5, order.getScooter().getId());
            statement.setLong(6, order.getRentalPoint().getId());
            statement.setLong(7, order.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ORDER_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createOrderFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("В базе данных нет заказов");
    }

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setOrderedAt(resultSet.getTimestamp("ordered_at").toLocalDateTime());
        order.setFinishedAt(resultSet.getTimestamp("finished_at").toLocalDateTime());
        order.setTotalPrice(resultSet.getBigDecimal("total_price"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status").toUpperCase()));
        order.setUser(userRepository.findById(resultSet.getLong("user_id")).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")));
        order.setScooter(scooterRepository.findById(resultSet.getLong("scooter_id")).orElseThrow(() -> new NoDataFoundException("Самокат не найден")));
        order.setRentalPoint(rentalPointRepository.findById(resultSet.getLong("rental_point_id")).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена")));
        return order;
    }

    private static void saveOrder(Order order, PreparedStatement statement) throws SQLException {
        statement.setLong(1, order.getId());
        statement.setTimestamp(2, Timestamp.valueOf(order.getOrderedAt()));
        statement.setTimestamp(3, Timestamp.valueOf(order.getFinishedAt()));
        statement.setBigDecimal(4, order.getTotalPrice());
        statement.setString(5, String.valueOf(order.getOrderStatus()).toUpperCase());
        statement.setInt(6, Math.toIntExact(order.getScooter().getId()));
        statement.setInt(7, Math.toIntExact(order.getRentalPoint().getId()));
        statement.setInt(8, Math.toIntExact(order.getUser().getId()));
    }

}
