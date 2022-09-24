package application;

import context.ApplicationContext;
import entity.Model;
import factory.BeanFactory;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class Application {

    public ApplicationContext run() {
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
    }

    public static void main(String[] args) {
        Application application = new Application();
        ApplicationContext context = application.run();
        UserRepository userRepository = new UserRepositoryImpl();
        RentalPointRepository rentalPointRepository = new RentalPointRepositoryImpl();
        ModelRepository modelRepository = new ModelRepositoryImpl();
        ScooterRepository scooterRepository = new ScooterRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();

        UserService userService = new UserServiceImpl();
        OrderService orderService = context.getBean(OrderServiceImpl.class);
        ScooterService scooterService = new ScooterServiceImpl();
        RentalPointService rentalPointService = new RentalPointServiceImpl();
        ModelService modelService = new ModelServiceImpl();
        //rentalPointService.save(new RentalPoint(1L,"fabrich", RentalPointStatus.OPEN));
        //modelService.save( new Model(0L,"Belarus","igor",11,300,500,290));
        //orderService.save(new Order(1L, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("0.59"), OrderStatus.OPEN,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //modelService.update(0L, new Model(null,"Belarus","igor",11,300,500,290));
        //orderService.update(0L,new Order(null, LocalDateTime.now(),LocalDateTime.now(),new BigDecimal("121212"), OrderStatus.CLOSE,userService.findById(11L).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")),scooterRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Самокат не найден")),rentalPointRepository.findById(0L).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена"))));
        //orderService.startRent(0L,1L,11L,0L);

        //orderService.startRent(0L, 13393333L,0L,0L);
        //orderService.finishRent(0L, 13393333L);
        /*User user = new User(2L,"Max", "Maximovich",20, Sex.MALE, UserStatus.FRIENDLY);
        userService.update(user);*/
        Model model = new Model();
        orderService.delete(3L);


    }
}
