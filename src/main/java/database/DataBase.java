package database;


import java.sql.*;

public class DataBase {
    public Connection connection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "1111";
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
