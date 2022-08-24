package repository.impl;

import entity.Scooter;
import repository.ScooterRepository;
import valid.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScooterRepositoryImpl implements ScooterRepository {

    private final List<Scooter> scooters = new ArrayList<>();

    @Override
    public void save(Scooter scooter) {
        Valid.isScooterPresent(scooters, scooter.getId());
        scooters.add(scooter);

    }

    @Override
    public void delete(Long id) {
        scooters.remove(Math.toIntExact(id));
    }

    @Override
    public void update(Long id, Scooter scooter) {
        Valid.isScooterPresent(scooters, id);
        Scooter updatedScooter = scooters.get(Math.toIntExact(id));
        updatedScooter.setModel(scooter.getModel());
        updatedScooter.setScooterStatus(scooter.getScooterStatus());
        updatedScooter.setPrice(scooter.getPrice());
        updatedScooter.setUser(scooter.getUser());
        updatedScooter.setRentalPoint(scooter.getRentalPoint());

    }

    @Override
    public Optional<Scooter> findById(Long id) {
        return scooters
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Scooter> findAll() {
        return scooters;
    }

}
