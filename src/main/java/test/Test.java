package test;

import entity.*;
import service.impl.OrderService;
import service.impl.ScooterService;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        LocalDateTime orderedAt = LocalDateTime.of(2022, 8, 18, 14, 5, 20);
        LocalDateTime finishedAt = LocalDateTime.of(2022, 8, 18, 17, 5, 20);
        User user = new User("Vashkevich", "Igor", 17, Sex.MALE, 0L, UserStatus.FRIENDLY);
        Models model1 = new Models("Xiomi", "XI", 2018, 100.4, 22.44, 250, 0L);
        RentalPoints rentalPoints = new RentalPoints("Sovetskaya 44 street", RentalPointsStatus.OPEN, 0L);
        Scooters scooter = new Scooters(0.05, model1, rentalPoints, ScootersStatus.AVAILABLE, user, 0L);
        OrderService priceCounter = new OrderService();
        Double price = priceCounter.counter(orderedAt, finishedAt, scooter.getPrice());
        Orders orders = new Orders(orderedAt, finishedAt, price, OrdersStatus.OPEN, user, scooter, rentalPoints, 0L);
        ScooterService renter = new ScooterService();
        renter.rent(scooter, orders, user, rentalPoints);
        System.out.println(scooter.getScootersStatus());
        System.out.println(orders.getOrdersStatus());
        renter.close(scooter, orders);
        System.out.println(scooter.getScootersStatus());
        System.out.println(orders.getOrdersStatus());
        System.out.println(orders);


    }
}
