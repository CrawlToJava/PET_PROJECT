package test;

import database.DataBase;
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
        UserRepository userRepository = new UserRepositoryImpl(dataBase);
        RentalPointRepository rentalPointRepository = new RentalPointRepositoryImpl(dataBase);
        ModelRepository modelRepository = new ModelRepositoryImpl(dataBase);
        ScooterRepository scooterRepository = new ScooterRepositoryImpl(dataBase,userRepository,rentalPointRepository,modelRepository);
        OrderRepository orderRepository = new OrderRepositoryImpl(dataBase,userRepository,scooterRepository,rentalPointRepository);

        UserService userService = new UserServiceImpl(userRepository,scooterRepository,orderRepository,rentalPointRepository);
        OrderService orderService = new OrderServiceImpl(userRepository,scooterRepository,orderRepository,rentalPointRepository);
        ScooterService scooterService = new ScooterServiceImpl(userRepository,scooterRepository,orderRepository,rentalPointRepository);
        RentalPointService rentalPointService = new RentalPointServiceImpl(userRepository,scooterRepository,orderRepository,rentalPointRepository);
        ModelService modelService = new ModelServiceImpl(modelRepository);
        //rentalPointService.save(new RentalPoint(1L,"fabrich", RentalPointStatus.OPEN));
        //modelService.save( new Model(0L,"Belarus","igor",11,300,500,290));
        //orderService.save(new Order(1L, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("0.59"), OrderStatus.OPEN,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //modelService.update(0L, new Model(null,"Belarus","igor",11,300,500,290));
        //orderService.update(0L,new Order(null, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("121212"), OrderStatus.CLOSE,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //orderService.startRent(0L,1L,11L,0L);

        //orderService.startRent(0L, 13393333L,0L,0L);
        //orderService.finishRent(0L, 13393333L);
        User user = new User(2L,"Max", "Maximovich",20, Sex.MALE, UserStatus.FRIENDLY);
        userService.update(user);
    }
}
