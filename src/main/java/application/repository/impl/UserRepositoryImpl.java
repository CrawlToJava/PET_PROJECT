package application.repository.impl;

import application.config.DataBase;
import application.entity.User;
import application.repository.UserRepository;
import customspring.metadata.Repository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Repository
@NoArgsConstructor
public class UserRepositoryImpl extends AbstractJpaRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(DataBase dataBase) {
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
