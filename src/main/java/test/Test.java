package test;

import entity.*;
import service.impl.OrderServiceImpl;

public class Test {
    public static void main(String[] args) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        User user = new User(0L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        User user1 = new User(1L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        orderServiceImpl.getUserRepository().save(user);
        orderServiceImpl.getUserRepository().save(user1);
        Model model1 = new Model(0L, "Xiomi", "XI", 2018, 100.4, 22.44, 250);
        RentalPoint rentalPoint = new RentalPoint(0L, "Sovetskaya 44 street", RentalPointStatus.OPEN);
        orderServiceImpl.getRentalPointRepository().save(rentalPoint);
        Scooter scooter = new Scooter(0L, 0.50, model1, rentalPoint, ScooterStatus.AVAILABLE, user);
        orderServiceImpl.getScooterRepository().save(scooter);
        Order order = new Order(0L, null, null, null, OrderStatus.OPEN, user, scooter, rentalPoint);
        orderServiceImpl.getOrderRepository().save(order);
        System.out.println(orderServiceImpl.getUserRepository().findAll());
        System.out.println(orderServiceImpl.getOrderRepository().findAll());
        System.out.println(orderServiceImpl.getRentalPointRepository().findAll());
        System.out.println(orderServiceImpl.getScooterRepository().findAll());
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
