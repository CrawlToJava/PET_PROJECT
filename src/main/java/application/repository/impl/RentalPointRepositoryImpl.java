package application.repository.impl;

import application.config.DataBase;
import application.entity.RentalPoint;
import application.repository.RentalPointRepository;
import customspring.metadata.Repository;
import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class RentalPointRepositoryImpl extends AbstractJpaRepositoryImpl<RentalPoint, Long> implements RentalPointRepository {

    public RentalPointRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(RentalPoint.class);
    }
}
