package repository.impl;

import config.DataBase;
import entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.OrderRepository;

import java.util.List;


public class OrderRepositoryImplImpl extends AbstractJpaRepositoryImpl<Order, Long> implements OrderRepository {

    public OrderRepositoryImplImpl(DataBase dataBase) {
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
