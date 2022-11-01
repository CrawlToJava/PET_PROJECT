package application.service;

import application.entity.Scooter;

import java.util.List;
import java.util.Optional;

public interface ScooterService {
    void save(Scooter scooter);

    void delete(Long id);

    void update(Scooter scooter);

    Optional<Scooter> findById(Long id);

    List<Scooter> findAll();
}
