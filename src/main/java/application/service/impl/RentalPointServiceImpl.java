package application.service.impl;

import application.entity.RentalPoint;
import application.exception.NoDataFoundException;
import application.repository.RentalPointRepository;
import application.service.RentalPointService;
import customspring.metadata.Autowired;
import customspring.metadata.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalPointServiceImpl implements RentalPointService {
    @Autowired
    private RentalPointRepository rentalPointRepository;

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
