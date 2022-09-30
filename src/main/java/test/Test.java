package test;

import database.DataBase;
import entity.*;
import repository.JPARepository;
import repository.impl.*;
import service.*;
import service.impl.*;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        JPARepository<User> userJPARepository = new UserRepositoryImpl(dataBase);
        JPARepository<Model> modelJPARepository = new ModelRepositoryImpl(dataBase);
        JPARepository<RentalPoint> rentalPointJPARepository = new RentalPointRepositoryImpl(dataBase);
        JPARepository<Scooter> scooterJPARepository = new ScooterRepositoryImpl(dataBase, userJPARepository, rentalPointJPARepository, modelJPARepository);
        JPARepository<Order> orderJPARepository = new OrderRepositoryImpl(dataBase, userJPARepository, scooterJPARepository, rentalPointJPARepository);

        UserService userService = new UserServiceImpl(userJPARepository, scooterJPARepository, orderJPARepository, rentalPointJPARepository);
        ScooterService scooterService = new ScooterServiceImpl(scooterJPARepository, userJPARepository, rentalPointJPARepository);
        RentalPointService rentalPointService = new RentalPointServiceImpl(userJPARepository, scooterJPARepository, orderJPARepository, rentalPointJPARepository);
        ModelService modelService = new ModelServiceImpl(modelJPARepository);
        OrderService orderService = new OrderServiceImpl(userJPARepository, scooterJPARepository, orderJPARepository, rentalPointJPARepository);
        //orderService.startRent(0L,27L,1L,0L);


    }
}
