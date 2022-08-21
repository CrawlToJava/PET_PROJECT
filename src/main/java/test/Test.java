package test;

import entity.*;
import service.impl.OrdersService;

public class Test {
    public static void main(String[] args) {
        OrdersService ordersService = new OrdersService();
        Users users = new Users(0L, "Vashkevich", "Igor", 17, Sex.MALE, UsersStatus.FRIENDLY);
        Users users1 = new Users(1L, "Vashkevich", "Igor", 17, Sex.MALE, UsersStatus.FRIENDLY);
        ordersService.getUserRepository().save(users);
        ordersService.getUserRepository().save(users1);
        Models model1 = new Models(0L, "Xiomi", "XI", 2018, 100.4, 22.44, 250);
        RentalPoints rentalPoints = new RentalPoints(0L, "Sovetskaya 44 street", RentalPointsStatus.OPEN);
        ordersService.getRentalPointsRepository().save(rentalPoints);
        Scooters scooter = new Scooters(0L, 0.50, model1, rentalPoints, ScootersStatus.AVAILABLE, users);
        ordersService.getScootersRepository().save(scooter);
        Orders orders = new Orders(0L, null, null, null, OrdersStatus.OPEN, users, scooter, rentalPoints);
        ordersService.getOrdersRepository().save(orders);
        System.out.println(ordersService.getUserRepository().findAll());
        System.out.println(ordersService.getOrdersRepository().findAll());
        System.out.println(ordersService.getRentalPointsRepository().findAll());
        System.out.println(ordersService.getScootersRepository().findAll());
        ordersService.startRent(0L, 0L, 0L, 0L);
        System.out.println(orders.getOrdersStatus());
        System.out.println(scooter.getScootersStatus());
        ordersService.finishRent(0L, 0L);
        System.out.println(orders.getTotalPrice());
        System.out.println(scooter.getScootersStatus());
        System.out.println(orders.getOrdersStatus());
        System.out.println(orders.getOrderedAt());
        System.out.println(orders.getFinishedAt());

    }

}
