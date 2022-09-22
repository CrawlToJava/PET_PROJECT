package repository.impl;

import config.DataBase;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.UserRepository;

import java.util.List;


public class UserRepositoryImplImpl extends AbstractJpaRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImplImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(User.class);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        DataBase dataBase = new DataBase();
        Session session = dataBase.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("from User where lastName = :lastName", User.class).setParameter("lastName", lastName).getResultList();
        transaction.commit();
        session.close();
        return userList;
    }
}
