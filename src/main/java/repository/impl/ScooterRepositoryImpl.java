package repository.impl;

import entity.Scooter;
import repository.ScooterRepository;
import exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScooterRepositoryImpl implements ScooterRepository {

    private final List<Scooter> scooters = new ArrayList<>();

    @Override
    public void save(Scooter scooter) {
        if (isPresent(scooters, scooter.getId())) {
            throw new RuntimeException("Электросамокаи с таким id уже есть");
        } else {
            scooters.add(scooter);
        }
    }

    @Override
    public void delete(Long id) {
        scooters.remove(Math.toIntExact(id));
    }

    @Override
    public void update(Long id, Scooter scooter) {
        if (isPresent(scooters, id)) {
            Scooter updatedScooter = scooters.get(Math.toIntExact(id));
            updatedScooter.setModel(scooter.getModel());
            updatedScooter.setScooterStatus(scooter.getScooterStatus());
            updatedScooter.setPrice(scooter.getPrice());
            updatedScooter.setUser(scooter.getUser());
            updatedScooter.setRentalPoint(scooter.getRentalPoint());
        } else {
            throw new NoSuchElementException("Электросамокат не найден");
        }
    }

    @Override
    public Optional<Scooter> findById(Long id) {
        return scooters.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public List<Scooter> findAll() {
        return scooters;
    }

    public boolean isPresent(List<Scooter> scooterList, Long id) {
        return scooterList.stream().anyMatch(u -> u.getId().equals(id));
    }
}
