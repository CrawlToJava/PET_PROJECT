package test;

import entity.*;
import service.impl.OrderService;

public class Test {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        User user = new User(0L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        User user1 = new User(1L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        orderService.getUserRepository().save(user);
        orderService.getUserRepository().save(user1);
        Model model1 = new Model(0L, "Xiomi", "XI", 2018, 100.4, 22.44, 250);
        RentalPoint rentalPoint = new RentalPoint(0L, "Sovetskaya 44 street", RentalPointStatus.OPEN);
        orderService.getRentalPointRepository().save(rentalPoint);
        Scooter scooter = new Scooter(0L, 0.50, model1, rentalPoint, ScooterStatus.AVAILABLE, user);
        orderService.getScooterRepository().save(scooter);
        Order order = new Order(0L, null, null, 0, OrderStatus.OPEN, user, scooter, rentalPoint);
        orderService.getOrderRepository().save(order);
        System.out.println(orderService.getUserRepository().findAll());
        System.out.println(orderService.getOrderRepository().findAll());
        System.out.println(orderService.getRentalPointRepository().findAll());
        System.out.println(orderService.getScooterRepository().findAll());
        orderService.startRent(0L, 0L, 0L, 0L);
        System.out.println(order.getOrderStatus());
        System.out.println(scooter.getScooterStatus());
        orderService.finishRent(0L, 0L);
        System.out.println(order.getTotalPrice());
        System.out.println(scooter.getScooterStatus());
        System.out.println(order.getOrderStatus());
        System.out.println(order.getOrderedAt());
        System.out.println(order.getFinishedAt());

    }

}
