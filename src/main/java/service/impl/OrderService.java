package service.impl;

import entity.*;
import lombok.Getter;
import lombok.SneakyThrows;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import repository.impl.OrderRepositoryImpl;
import repository.impl.RentalPointRepositoryImpl;
import repository.impl.ScooterRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.PriceCounter;
import service.Renter;
import valid.Valid;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Getter
public class OrderService implements Renter, PriceCounter {

    private final UserRepository userRepository = new UserRepositoryImpl();

    private final ScooterRepository scooterRepository = new ScooterRepositoryImpl();

    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    private final RentalPointRepository rentalPointRepository = new RentalPointRepositoryImpl();

    @SneakyThrows
    @Override
    public void startRent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId) {
        Optional<Scooter> scootersOptional = scooterRepository.findById(scootersId);
        Scooter scooter = scootersOptional.orElseThrow();
        Optional<Order> ordersOptional = orderRepository.findById(ordersId);
        Order order = ordersOptional.orElseThrow();
        Optional<User> usersOptional = userRepository.findById(userId);
        User user = usersOptional.orElseThrow();
        Optional<RentalPoint> rentalPointsOptional = rentalPointRepository.findById(rentalPointsId);
        RentalPoint rentalPoint = rentalPointsOptional.orElseThrow();
        Valid.isRentAvailable(scooter, user, rentalPoint);
        scooter.setScooterStatus(ScooterStatus.BOOKED);
        order.setOrderStatus(OrderStatus.OPEN);
        order.setOrderedAt(LocalDateTime.now());
    }

    @Override
    public void finishRent(Long scootersId, Long ordersId) {
        Optional<Scooter> scootersOptional = scooterRepository.findById(scootersId);
        Scooter scooter = scootersOptional.orElseThrow();
        Optional<Order> ordersOptional = orderRepository.findById(ordersId);
        Order order = ordersOptional.orElseThrow();
        Valid.isFinishRentAvailable(scooter);
        scooter.setScooterStatus(ScooterStatus.AVAILABLE);
        order.setOrderStatus(OrderStatus.CLOSE);
        order.setFinishedAt(LocalDateTime.now());
        order.setTotalPrice(counter(order.getOrderedAt()
                , order.getFinishedAt()
                , scooter.getPrice()));
    }

    @Override
    public Double counter(LocalDateTime orderedAt, LocalDateTime finishedAt, Double price) {
        Long start = orderedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long end = finishedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return price * (end - start);
    }
}
