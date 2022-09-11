package service;

import entity.RentalPoint;

import java.util.List;
import java.util.Optional;

public interface RentalPointService {
    void save(RentalPoint rentalPoint);

    void delete(Long id);

    void update(RentalPoint rentalPoint);

    Optional<RentalPoint> findById(Long id);

    List<RentalPoint> findAll();
}
