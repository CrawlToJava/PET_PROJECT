package application.repository.impl;

import application.config.DataBase;
import application.entity.Order;
import application.repository.OrderRepository;
import customspring.metadata.Repository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Repository
@NoArgsConstructor
public class OrderRepositoryImpl extends AbstractJpaRepositoryImpl<Order, Long> implements OrderRepository {

    public OrderRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Order.class);
    }

    @Override
    public List<Order> findAll() {
        DataBase dataBase = new DataBase();
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        List<Order> orderList = session.createQuery("from Order o join fetch o.user join fetch o.rentalPoint join fetch o.scooter").getResultList();
        transaction.commit();
        session.close();
        return orderList;
    }
}
