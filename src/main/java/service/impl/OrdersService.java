package service.impl;

import entity.*;
import lombok.Getter;
import lombok.SneakyThrows;
import repository.OrdersRepository;
import repository.RentalPointsRepository;
import repository.ScootersRepository;
import repository.UserRepository;
import repository.impl.OrdersRepositoryImpl;
import repository.impl.RentalPointsRepositoryImpl;
import repository.impl.ScootersRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.PriceCounter;
import service.Renter;
import valid.Valid;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Getter
public class OrdersService implements Renter, PriceCounter {

    private final UserRepository userRepository = new UserRepositoryImpl();

    private final ScootersRepository scootersRepository = new ScootersRepositoryImpl();

    private final OrdersRepository ordersRepository = new OrdersRepositoryImpl();

    private final RentalPointsRepository rentalPointsRepository = new RentalPointsRepositoryImpl();

    @SneakyThrows
    @Override
    public void rent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId) {
        Optional<Scooters> scootersOptional = scootersRepository.findById(scootersId);
        Scooters scooters = scootersOptional.orElseThrow();
        Optional<Orders> ordersOptional = ordersRepository.findById(ordersId);
        Orders orders = ordersOptional.orElseThrow();
        Optional<Users> usersOptional = userRepository.findById(userId);
        Users users = usersOptional.orElseThrow();
        Optional<RentalPoints> rentalPointsOptional = rentalPointsRepository.findById(rentalPointsId);
        RentalPoints rentalPoints = rentalPointsOptional.orElseThrow();
        Valid.isRentAvailable(scooters, users, rentalPoints);
        scooters.setScootersStatus(ScootersStatus.BOOKED);
        orders.setOrdersStatus(OrdersStatus.OPEN);
        orders.setOrderedAt(LocalDateTime.now());
    }

    @Override
    public void finishRent(Long scootersId, Long ordersId) {
        Optional<Scooters> scootersOptional = scootersRepository.findById(scootersId);
        Scooters scooters = scootersOptional.orElseThrow();
        Optional<Orders> ordersOptional = ordersRepository.findById(ordersId);
        Orders orders = ordersOptional.orElseThrow();
        Valid.isCloseAvailable(scooters);
        scooters.setScootersStatus(ScootersStatus.AVAILABLE);
        orders.setOrdersStatus(OrdersStatus.CLOSE);
        orders.setFinishedAt(LocalDateTime.now());
        orders.setTotalPrice(counter(orders.getOrderedAt()
                , orders.getFinishedAt()
                , scooters.getPrice()));
    }

    @Override
    public Double counter(LocalDateTime orderedAt, LocalDateTime finishedAt, Double price) {
        Long start = orderedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long end = finishedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return price * (end - start);
    }
}
