package repository.impl;

import entity.RentalPoints;
import exceptions.NoSuchElementException;
import repository.RentalPointsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalPointsRepositoryImpl implements RentalPointsRepository {
    private final List<RentalPoints> list = new ArrayList<>();

    @Override
    public void save(RentalPoints rentalPoint) {
        if (isPresent(list, rentalPoint.getId())) {
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
    public int size() {
        return list.size();
    }

    @Override
    public Optional<RentalPoints> findById(Long id) {
        return list.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Long id, RentalPoints rentalPoint) {
        if (isPresent(list, id)) {
            RentalPoints updatedRentalPoint = list.get(Math.toIntExact(id));
            updatedRentalPoint.setRentalPointsStatus(rentalPoint.getRentalPointsStatus());
            updatedRentalPoint.setLocation(rentalPoint.getLocation());
        } else {
            throw new NoSuchElementException("Точка проката не найдена");
        }
    }

    public boolean isPresent(List<RentalPoints> pointsList, Long id) {
        return pointsList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<RentalPoints> findAll() {
        return list;
    }
}
