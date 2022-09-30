package repository.impl;

import database.DataBase;
import database.Queryses;
import entity.Sex;
import entity.User;
import entity.UserStatus;
import exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.JPARepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepositoryImpl implements JPARepository<User> {
    private DataBase dataBase;

    @Override
    public void save(User user) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.SAVE_USER);
            saveUser(user, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.DELETE_USER + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_USER_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.UPDATE_USER + user.getId())) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, Math.toIntExact(user.getAge()));
            statement.setString(4, String.valueOf(user.getSex()).toUpperCase());
            statement.setString(5, String.valueOf(user.getUserStatus()).toUpperCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createUserFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("В базе данных нет пользователей");
    }

    /*@Override
    public List<User> findBySecondName(String lastName) {
        List<User> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_USER_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createUserFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("Пользователей с такой фамилией нет в базе данных");
    }*/

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAge(resultSet.getInt("age"));
        user.setUserStatus(UserStatus.valueOf(resultSet.getString("status").toUpperCase()));
        user.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));
        return user;
    }

    private void saveUser(User user, PreparedStatement statement) throws SQLException {
        statement.setLong(1, user.getId());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setInt(4, Math.toIntExact(user.getAge()));
        statement.setString(5, String.valueOf(user.getSex()).toUpperCase());
        statement.setString(6, String.valueOf(user.getUserStatus()).toUpperCase());
    }
}
