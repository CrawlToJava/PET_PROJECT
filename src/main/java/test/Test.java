package test;

import database.DataBase;
import entity.User;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        DataBase dataBase = new DataBase();
        UserRepository userJPARepository = new UserRepositoryImpl(dataBase);
        ModelRepository modelJPARepository = new ModelRepositoryImpl(dataBase);
        RentalPointRepository rentalPointJPARepository = new RentalPointRepositoryImpl(dataBase);
        ScooterRepository scooterJPARepository = new ScooterRepositoryImpl(dataBase, userJPARepository, rentalPointJPARepository, modelJPARepository);
        OrderRepository orderRepository= new OrderRepositoryImpl(dataBase, userJPARepository, scooterJPARepository, rentalPointJPARepository);

        UserService userService = new UserServiceImpl(userJPARepository, scooterJPARepository, orderRepository, rentalPointJPARepository);
        ScooterService scooterService = new ScooterServiceImpl(scooterJPARepository, userJPARepository, rentalPointJPARepository);
        RentalPointService rentalPointService = new RentalPointServiceImpl(userJPARepository, scooterJPARepository, orderRepository, rentalPointJPARepository);
        ModelService modelService = new ModelServiceImpl(modelJPARepository);
        OrderService orderService = new OrderServiceImpl(userJPARepository, scooterJPARepository, orderRepository, rentalPointJPARepository);
        //orderService.startRent(0L,27L,1L,0L);
        orderService.finishRent(0L,27L);


    }
}
