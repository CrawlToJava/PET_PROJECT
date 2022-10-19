package repository.impl;

import config.DataBase;
import entity.Scooter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ScooterRepository;

import java.util.List;


public class ScooterRepositoryImplImpl extends AbstractJpaRepositoryImpl<Scooter, Long> implements ScooterRepository {
    public ScooterRepositoryImplImpl(DataBase dataBase) {
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
