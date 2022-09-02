package service;

import entity.Scooter;

import java.util.List;
import java.util.Optional;

public interface ScooterService {
    void save(Scooter scooter);

    void delete(Long id);

    void update(Long id, Scooter scooter);

    Optional<Scooter> findById(Long id);

    List<Scooter> findAll();
}
