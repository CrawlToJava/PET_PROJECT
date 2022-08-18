package repository;

import entity.RentalPoints;
import entity.Scooters;

import java.util.List;
import java.util.Optional;

public interface RentalPointsRepository {
    void save(RentalPoints rentalPoint);

    void delete(Long id);

    int size();

    void update(Long id, RentalPoints rentalPoint);

    Optional<RentalPoints> findById(Long id);

    List<RentalPoints> findAll();
}
