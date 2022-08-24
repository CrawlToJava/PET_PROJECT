package test;

import entity.*;
import repository.impl.OrderRepositoryImpl;
import repository.impl.RentalPointRepositoryImpl;
import repository.impl.ScooterRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(new UserRepositoryImpl(), new ScooterRepositoryImpl(), new OrderRepositoryImpl(), new RentalPointRepositoryImpl());
        User user = new User(0L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        Model model1 = new Model(0L, "Xiomi", "XI", 2018, 100.4, 22.44, 250);
        RentalPoint rentalPoint = new RentalPoint(0L, "Sovetskaya 44 street", RentalPointStatus.OPEN);
        Scooter scooter = new Scooter(0L, new BigDecimal("0.50"), model1, rentalPoint, ScooterStatus.AVAILABLE, user);
        Order order = new Order(0L, null, null, null, OrderStatus.OPEN, user, scooter, rentalPoint);
        orderServiceImpl.startRent(0L, 0L, 0L, 0L);
        System.out.println(order.getOrderStatus());
        System.out.println(scooter.getScooterStatus());
        orderServiceImpl.finishRent(0L, 0L);
        System.out.println(order.getTotalPrice());
        System.out.println(scooter.getScooterStatus());
        System.out.println(order.getOrderStatus());
        System.out.println(order.getOrderedAt());
        System.out.println(order.getFinishedAt());

    }

}
