package repository;

import entity.Scooters;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface ScooterRepository {
    void save(Scooters scooter);

    void delete(Long id);

    int size();

    void update(Long id, Scooters scooter);

    Optional<Scooters> findById(Long id);

    List<Scooters> findAll();
}
