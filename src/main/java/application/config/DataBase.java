package application.config;


import application.entity.*;
import customspring.metadata.Component;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

@Component
public class DataBase {
    public Session getSession() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "1111");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Order.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(RentalPoint.class)
                .addAnnotatedClass(Model.class)
                .addAnnotatedClass(Scooter.class);
        Session session = null;
        try {
            SessionFactory sessionFactory = configuration.addProperties(properties).buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return session;
    }
}
