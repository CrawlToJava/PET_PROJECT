package config.impl;

import config.Configuration;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import java.util.Map;

public class ConfigurationImpl implements Configuration {

    @Override
    public String getPackageToScan() {
        return ".idea";
    }

    @Override
    public Map<Class, Class> getInterfaceToImplementation() {
        return Map.of(OrderRepository.class, OrderRepositoryImpl.class
                , OrderService.class, OrderServiceImpl.class
                , ModelRepository.class, ModelRepositoryImpl.class
                , ModelService.class, ModelServiceImpl.class
                , RentalPointRepository.class, RentalPointRepositoryImpl.class
                , RentalPointService.class, RentalPointServiceImpl.class
                , ScooterRepository.class, ScooterRepositoryImpl.class
                , ScooterService.class, ScooterServiceImpl.class
                , UserRepository.class, UserRepositoryImpl.class
                , UserService.class, UserServiceImpl.class);
    }
}
