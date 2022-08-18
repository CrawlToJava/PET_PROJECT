package repository.impl;

import entity.Scooters;
import repository.ScootersRepository;
import exceptions.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScootersRepositoryImpl implements ScootersRepository {

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
    public void update(Long id, Scooters scooter) {
        if (isPresent(scooters, id)) {
            Scooters updatedScooter = scooters.get(Math.toIntExact(id));
            updatedScooter.setModel(scooter.getModel());
            updatedScooter.setScootersStatus(scooter.getScootersStatus());
            updatedScooter.setPrice(scooter.getPrice());
            updatedScooter.setUsers(scooter.getUsers());
            updatedScooter.setRentalPoint(scooter.getRentalPoint());
        } else {
            throw new NoSuchElementException("Электросамокат не найден");
        }
    }

    @Override
    public Optional<Scooters> findById(Long id) {
        return scooters.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public List<Scooters> findAll() {
        return scooters;
    }

    public boolean isPresent(List<Scooters> scootersList, Long id) {
        return scootersList.stream().anyMatch(u -> u.getId().equals(id));
    }
}
