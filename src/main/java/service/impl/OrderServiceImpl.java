package service.impl;

import entity.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.OrderService;
import valid.Valid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final ScooterRepository scooterRepository;

    private final OrderRepository orderRepository;

    private final RentalPointRepository rentalPointRepository;


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
        order.setTotalPrice(countOrderPrice(order.getOrderedAt()
                , order.getFinishedAt()
                , scooter.getPrice()));
    }

    @Override
    public BigDecimal countOrderPrice(LocalDateTime orderedAt, LocalDateTime finishedAt, BigDecimal price) {
        BigDecimal start = BigDecimal.valueOf(orderedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        BigDecimal end = BigDecimal.valueOf(finishedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        return price.multiply(start.subtract(end));
    }
}
