package repository.impl;

import config.DataBase;
import entity.RentalPoint;
import repository.RentalPointRepository;


public class RentalPointRepositoryImplImpl extends AbstractJpaRepositoryImpl<RentalPoint, Long> implements RentalPointRepository {
    public RentalPointRepositoryImplImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(RentalPoint.class);
    }
}
