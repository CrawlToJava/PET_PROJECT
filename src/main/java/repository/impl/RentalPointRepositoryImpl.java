package repository.impl;

import database.DataBase;
import database.Queryses;
import entity.RentalPoint;
import entity.RentalPointStatus;
import exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.RentalPointRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RentalPointRepositoryImpl implements RentalPointRepository {

    private DataBase dataBase;

    @Override
    public void save(RentalPoint rentalPoint) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.SAVE_RENTAL_POINT);
            saveRentalPoint(rentalPoint, statement);
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
            statement = postgres.prepareStatement(Queryses.DELETE_RENTAL_POINT + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, RentalPoint rentalPoint) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.UPDATE_RENTAL_POINT + id)) {
            statement.setString(1, rentalPoint.getLocation());
            statement.setString(2, String.valueOf(rentalPoint.getRentalPointsStatus()).toUpperCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<RentalPoint> findById(Long id) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_RENTAL_POINT_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createRentalPointFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<RentalPoint> findAll() {
        List<RentalPoint> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ALL_RENTAL_POINTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createRentalPointFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("В базе данных нет точек проката");
    }

    private void saveRentalPoint(RentalPoint rentalPoint, PreparedStatement statement) throws SQLException {
        statement.setLong(1, rentalPoint.getId());
        statement.setString(2, rentalPoint.getLocation());
        statement.setString(3, String.valueOf(rentalPoint.getRentalPointsStatus()).toUpperCase());
    }

    private RentalPoint createRentalPointFromResultSet(ResultSet resultSet) throws SQLException {
        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(resultSet.getLong("id"));
        rentalPoint.setLocation(resultSet.getString("location"));
        rentalPoint.setRentalPointsStatus(RentalPointStatus.valueOf(resultSet.getString("scooter_status")));
        return rentalPoint;
    }
}
