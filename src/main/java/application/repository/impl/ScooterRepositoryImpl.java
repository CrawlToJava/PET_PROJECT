package application.repository.impl;

import application.config.DataBase;
import application.entity.Scooter;
import application.repository.ScooterRepository;
import customspring.metadata.Repository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Repository
@NoArgsConstructor
public class ScooterRepositoryImpl extends AbstractJpaRepositoryImpl<Scooter, Long> implements ScooterRepository {

    public ScooterRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Scooter.class);
    }

    @Override
    public List<Scooter> findAll() {
        DataBase dataBase = new DataBase();
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        List<Scooter> scooterList = session.createQuery("from Scooter s join fetch s.model").getResultList();
        transaction.commit();
        session.close();
        return scooterList;
    }
}
