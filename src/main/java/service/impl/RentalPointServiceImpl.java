package service.impl;

import entity.RentalPoint;
import exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.RentalPointRepository;
import service.RentalPointService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RentalPointServiceImpl implements RentalPointService {

    private final RentalPointRepository rentalPointRepository;

    @Override
    public void save(RentalPoint rentalPoint) {
        rentalPointRepository.save(rentalPoint);
    }

    @Override
    public void delete(Long id) {
        rentalPointRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Точки проката с таким id не существует"));
        rentalPointRepository.delete(id);
    }

    @Override
    public void update(RentalPoint rentalPoint) {
        rentalPointRepository.findById(rentalPoint.getId()).orElseThrow(() -> new NoDataFoundException("Точки проката с таким id не существует"));
        rentalPointRepository.update(rentalPoint);
    }

    @Override
    public Optional<RentalPoint> findById(Long id) {
        return Optional.ofNullable(rentalPointRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Точки проката с таким id не существует")));
    }

    @Override
    public List<RentalPoint> findAll() {
        List<RentalPoint> rentalPointList = rentalPointRepository.findAll();
        if (rentalPointList.isEmpty()) {
            throw new NoDataFoundException("Таблица с точками проката пустая");
        }
        return rentalPointList;
    }
}
