package test;

import database.DataBase;
import entity.*;
import repository.UserRepository;
import repository.impl.OrderRepositoryImpl;
import repository.impl.RentalPointRepositoryImpl;
import repository.impl.ScooterRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.ScooterService;
import service.UserService;
import service.impl.ScooterServiceImpl;
import service.impl.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        UserRepository userRepository = new UserRepositoryImpl(dataBase);
        UserService userService = new UserServiceImpl(userRepository, new ScooterRepositoryImpl(dataBase,userRepository), new OrderRepositoryImpl(dataBase), new RentalPointRepositoryImpl(dataBase));
        ScooterService scooterService = new ScooterServiceImpl(userRepository, new ScooterRepositoryImpl(dataBase), new OrderRepositoryImpl(dataBase), new RentalPointRepositoryImpl(dataBase));
        System.out.println(userService.findById(3L).orElseThrow(() -> new RuntimeException("такого пользователя с айди нет")));
        Model model1 = new Model(0L, "Xiomi", "XI", 2018, 100.4, 22.44, 250);
        RentalPoint rentalPoint = new RentalPoint(0L, "Sovetskaya 44 street", RentalPointStatus.OPEN);
        User user = new User(202020L, "Vashkevich", "Igor", 17, Sex.MALE, UserStatus.FRIENDLY);
        //scooterService.save(new Scooter(0L, new BigDecimal("0.50"), rentalPoint, model1, ScooterStatus.AVAILABLE, user));
        //System.out.println(scooterService.findById(0L).orElseThrow(() -> new RuntimeException("такого самоката с айди нет")));
        System.out.println(userService.findAll());
        System.out.println(scooterService.findAll());
    }
}
