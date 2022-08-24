package repository.impl;

import entity.RentalPoint;
import repository.RentalPointRepository;
import valid.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalPointRepositoryImpl implements RentalPointRepository {
    private final List<RentalPoint> list = new ArrayList<>();

    @Override
    public void save(RentalPoint rentalPoint) {
        Valid.isRentalPointPresent(list, rentalPoint.getId());
        list.add(rentalPoint);
    }

    @Override
    public void delete(Long id) {
        list.remove(Math.toIntExact(id));
    }

    @Override
    public void update(Long id, RentalPoint rentalPoint) {
        Valid.isRentalPointPresent(list, rentalPoint.getId());
        RentalPoint updatedRentalPoint = list.get(Math.toIntExact(id));
        updatedRentalPoint.setRentalPointsStatus(rentalPoint.getRentalPointsStatus());
        updatedRentalPoint.setLocation(rentalPoint.getLocation());
    }

    @Override
    public Optional<RentalPoint> findById(Long id) {
        return list
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<RentalPoint> findAll() {
        return list;
    }
}
