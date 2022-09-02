package repository;

import entity.RentalPoint;

import java.util.List;
import java.util.Optional;

public interface RentalPointRepository {
    void save(RentalPoint rentalPoint);

    void delete(Long id);

    void update(Long id, RentalPoint rentalPoint);

    Optional<RentalPoint> findById(Long id);

    List<RentalPoint> findAll();
}
