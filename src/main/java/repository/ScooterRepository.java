package repository;

import entity.Scooter;


import java.util.List;
import java.util.Optional;

public interface ScooterRepository {
    void save(Scooter scooter);

    void delete(Long id);

    void update(Scooter scooter);

    Optional<Scooter> findById(Long id);

    List<Scooter> findAll();
}
