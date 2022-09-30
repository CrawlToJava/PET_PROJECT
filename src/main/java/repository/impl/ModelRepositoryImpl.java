package repository.impl;

import database.DataBase;
import database.Queryses;
import entity.Model;
import exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.ModeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ModelRepositoryImpl implements ModeRepository {
    private DataBase dataBase;

    @Override
    public void save(Model model) {
        Connection postgres = dataBase.connection();
        PreparedStatement statement;
        try {
            statement = postgres.prepareStatement(Queryses.SAVE_MODEL);
            saveModel(model, statement);
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
            statement = postgres.prepareStatement(Queryses.DELETE_MODEL + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model model) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.UPDATE_MODEL + model.getId())) {
            statement.setString(1, model.getBrand());
            statement.setString(2, model.getModel());
            statement.setInt(3, model.getYear());
            statement.setDouble(4, model.getMaxLoad());
            statement.setDouble(5, model.getRange());
            statement.setDouble(6, model.getPower());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Model> findById(Long id) {
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_MODEL_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createModelFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Model> findAll() {
        List<Model> list = new ArrayList<>();
        Connection postgres = dataBase.connection();
        try (PreparedStatement statement = postgres.prepareStatement(Queryses.FIND_ALL_MODELS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createModelFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("В базе данных нет моделей самокатов");
    }

    private Model createModelFromResultSet(ResultSet resultSet) throws SQLException {
        Model model = new Model();
        model.setModel(resultSet.getString("model"));
        model.setBrand(resultSet.getString("brand"));
        model.setId(resultSet.getLong("id"));
        model.setYear(resultSet.getInt("year"));
        model.setMaxLoad(resultSet.getDouble("max"));
        model.setRange(resultSet.getDouble("range"));
        model.setPower(resultSet.getDouble("power"));
        return model;
    }

    private static void saveModel(Model model, PreparedStatement statement) throws SQLException {
        statement.setInt(1, Math.toIntExact(model.getId()));
        statement.setString(2, model.getBrand());
        statement.setString(3, model.getModel());
        statement.setInt(4, model.getYear());
        statement.setDouble(5, model.getMaxLoad());
        statement.setDouble(6, model.getRange());
        statement.setDouble(7, model.getPower());
    }

}
