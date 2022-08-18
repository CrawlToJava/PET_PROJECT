package repository.impl;

import entity.Scooters;
import exceptions.NoSuchElementException;
import repository.ScooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScooterRepositoryImpl implements ScooterRepository {

    private final List<Scooters> scooters = new ArrayList<>();

    @Override
    public void save(Scooters scooter) {
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
    public int size() {
        return scooters.size();
    }

    @Override
    public Optional<Scooters> findById(Long id) {
        return scooters.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Long id, Scooters scooter) {
        if (isPresent(scooters, id)) {
            Scooters updatedScooter = scooters.get(Math.toIntExact(id));
            updatedScooter.setModel(scooter.getModel());
            updatedScooter.setScootersStatus(scooter.getScootersStatus());
            updatedScooter.setPrice(scooter.getPrice());
            updatedScooter.setUser(scooter.getUser());
            updatedScooter.setRentalPoint(scooter.getRentalPoint());
        } else {
            throw new NoSuchElementException("Электросамокат не найден");
        }
    }

    public boolean isPresent(List<Scooters> scootersList, Long id) {
        return scootersList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<Scooters> findAll() {
        return scooters;
    }
}
