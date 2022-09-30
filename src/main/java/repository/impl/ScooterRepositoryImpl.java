package repository.impl;

import database.DataBase;
import database.Queryses;
import entity.*;
import exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScooterRepositoryImpl implements ScooterRepository {

    private DataBase dataBase;

    private UserRepository userRepository;

    private RentalPointRepository rentalPointRepository;

    private ModeRepository modelRepository;


    @Override
    public void save(Scooter scooter) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.SAVE_SCOOTER);
            saveScooter(scooter, statement);
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
            statement = postgres.prepareStatement(Queryses.DELETE_SCOOTER + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Scooter> findById(Long id) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_SCOOTER_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createScooterFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Scooter scooter) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.UPDATE_SCOOTER + scooter.getId())) {
            statement.setBigDecimal(1, scooter.getPrice());
            statement.setString(4, String.valueOf(scooter.getScooterStatus()).toUpperCase());
            statement.setInt(2, Math.toIntExact(scooter.getModel().getId()));
            statement.setInt(3, Math.toIntExact(scooter.getRentalPoint().getId()));
            statement.setInt(5, Math.toIntExact(scooter.getUser().getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Scooter> findAll() {
        List<Scooter> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ALL_SCOOTERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createScooterFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("В базе данных нет электросамокатов");
    }

    private void saveScooter(Scooter scooter, PreparedStatement statement) throws SQLException {
        statement.setLong(1, scooter.getId());
        statement.setBigDecimal(2, scooter.getPrice());
        statement.setString(3, String.valueOf(scooter.getScooterStatus()).toUpperCase());
        statement.setInt(4, Math.toIntExact(scooter.getModel().getId()));
        statement.setInt(5, Math.toIntExact(scooter.getRentalPoint().getId()));
        statement.setInt(6, Math.toIntExact(scooter.getUser().getId()));
    }

    private Scooter createScooterFromResultSet(ResultSet resultSet) throws SQLException {
        Scooter scooter = new Scooter();
        scooter.setId(resultSet.getLong("id"));
        scooter.setPrice(resultSet.getBigDecimal("price"));
        scooter.setScooterStatus(ScooterStatus.valueOf(resultSet.getString("scooter_status").toUpperCase()));
        scooter.setRentalPoint(rentalPointRepository.findById(resultSet.getLong("rental_point_id")).orElseThrow(() -> new NoDataFoundException("Точка проката не найдена")));
        scooter.setUser(userRepository.findById(resultSet.getLong("user_id")).orElseThrow(() -> new NoDataFoundException("Пользователь не найден")));
        scooter.setModel(modelRepository.findById(resultSet.getLong("model_id")).orElseThrow(() -> new NoDataFoundException("Модель электросамоката не найдена")));
        return scooter;
    }
}
