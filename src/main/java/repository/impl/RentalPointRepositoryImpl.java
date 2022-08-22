package repository.impl;

import entity.RentalPoint;
import repository.RentalPointRepository;
import exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalPointRepositoryImpl implements RentalPointRepository {
    private final List<RentalPoint> list = new ArrayList<>();

    @Override
    public void save(RentalPoint rentalPoint) {
        if (isPresent(list,rentalPoint.getId())) {
            throw new RuntimeException("Точка проката с таким id уже есть");
        } else {
            list.add(rentalPoint);
        }
    }

    @Override
    public void delete(Long id) {
        list.remove(Math.toIntExact(id));
    }

    @Override
    public void update(Long id, RentalPoint rentalPoint) {
        if (isPresent(list, id)) {
            RentalPoint updatedRentalPoint = list.get(Math.toIntExact(id));
            updatedRentalPoint.setRentalPointsStatus(rentalPoint.getRentalPointsStatus());
            updatedRentalPoint.setLocation(rentalPoint.getLocation());
        } else {
            throw new NoSuchElementException("Точка проката не найдена");
        }
    }

    @Override
    public Optional<RentalPoint> findById(Long id) {
        return list.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public List<RentalPoint> findAll() {
        return list;
    }

    public boolean isPresent(List<RentalPoint> pointsList, Long id) {
        return pointsList.stream().anyMatch(u -> u.getId().equals(id));
    }
}
