package application.repository.impl;

import application.config.DataBase;
import application.exception.NoDataFoundException;
import application.repository.AbstractJpaRepository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class AbstractJpaRepositoryImpl<T, ID> implements AbstractJpaRepository<T, ID> {
    private Class<T> clazz;

    private DataBase dataBase;

    public AbstractJpaRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Optional<T> findById(ID id) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        Optional<T> entity = Optional.ofNullable(dataBase.getSession().get(clazz, id));
        transaction.commit();
        session.close();
        return entity;
    }

    public List<T> findAll() {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        List<T> listOfEntity = session.createQuery("from " + clazz.getName()).getResultList();
        transaction.commit();
        return listOfEntity;
    }

    public void save(T entity) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    public void delete(ID id) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        Optional<T> entity = Optional.ofNullable(session.get(clazz, id));
        session.remove(entity.orElseThrow(() -> new NoDataFoundException("Сущность не найдена")));
        transaction.commit();
        session.close();
    }
}
