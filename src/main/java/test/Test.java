package test;

import config.DataBase;
import entity.Sex;
import entity.User;
import entity.UserStatus;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        UserRepository userRepository = new UserRepositoryImplImpl(dataBase);
        RentalPointRepository rentalPointRepository = new RentalPointRepositoryImplImpl(dataBase);
        ModelRepository modelRepository = new ModelRepositoryImplImpl(dataBase);
        ScooterRepository scooterRepository = new ScooterRepositoryImplImpl(dataBase);
        OrderRepository orderRepository = new OrderRepositoryImplImpl(dataBase);

        UserService userService = new UserServiceImpl(userRepository);
        OrderService orderService = new OrderServiceImpl(userRepository, scooterRepository, orderRepository, rentalPointRepository);
        ScooterService scooterService = new ScooterServiceImpl(scooterRepository);
        RentalPointService rentalPointService = new RentalPointServiceImpl(rentalPointRepository);
        ModelService modelService = new ModelServiceImpl(modelRepository);
        userService.save(new User("Ilya", "Shendik", 17, Sex.MALE, UserStatus.FRIENDLY));
    }
}
