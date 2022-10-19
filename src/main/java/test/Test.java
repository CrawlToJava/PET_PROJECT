package test;

import config.DataBase;
import entity.Order;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        UserRepository userRepository = new UserRepositoryImplImpl(dataBase);
        RentalPointRepository rentalPointRepository = new RentalPointRepositoryImplImpl(dataBase);
        ModelRepository modelRepository = new ModelRepositoryImplImpl(dataBase);
        ScooterRepository scooterRepository = new ScooterRepositoryImplImpl(dataBase);
        OrderRepository orderRepository = new OrderRepositoryImplImpl(dataBase);

        UserService userService = new UserServiceImpl(userRepository, scooterRepository, orderRepository, rentalPointRepository);
        OrderService orderService = new OrderServiceImpl(userRepository, scooterRepository, orderRepository, rentalPointRepository);
        ScooterService scooterService = new ScooterServiceImpl(userRepository, scooterRepository, orderRepository, rentalPointRepository);
        RentalPointService rentalPointService = new RentalPointServiceImpl(userRepository, scooterRepository, orderRepository, rentalPointRepository);
        ModelService modelService = new ModelServiceImpl(modelRepository);
        //rentalPointService.save(new RentalPoint(1L,"fabrich", RentalPointStatus.OPEN));
        //modelService.save( new Model(0L,"Belarus","igor",11,300,500,290));
        //orderService.save(new Order(1L, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("0.59"), OrderStatus.OPEN,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //modelService.update(0L, new Model(null,"Belarus","igor",11,300,500,290));
        //orderService.update(0L,new Order(null, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("121212"), OrderStatus.CLOSE,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //orderService.startRent(0L,1L,11L,0L);

        //orderService.startRent(0L, 13393333L,0L,0L);
        //orderService.finishRent(0L, 13393333L);
        //userService.save(new User(1500L,"Valera","Ivashko",18, Sex.MALE, UserStatus.FRIENDLY));
        //orderService.startRent(0L,1910109191L,1500L,0L);
        //orderService.finishRent(0L,1910109191L);
        //System.out.println(userService.findById(1500L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")));
        //System.out.println(userService.findByLastName("Spurging1"));
        //userService.update(new User(10500L,"Serega","Derden",54, Sex.MALE, UserStatus.FRIENDLY));
        //System.out.println(rentalPointService.findAll());
        //System.out.println(scooterService.findAll());
        /*List<Order> listOrder = orderService.findAll();
        listOrder.forEach(order -> {
            RentalPoint rentalPoint = order.getRentalPoint();
            System.out.println(rentalPoint.getRentalPointsStatus());
        });*/
        //System.out.println(orderService.findById(27L).orElseThrow(() -> new NoDataFoundException("error")));

        //System.out.println(listOrder);
        //System.out.println(modelService.findById(0L));
        /*List<Order> orders = orderService.findAll();
        orders.forEach(order -> System.out.println(order.getUser()));*/

        List<Order> orders = orderService.findAll();
        orders.forEach(order -> System.out.println(order.getUser()));

    }
}
