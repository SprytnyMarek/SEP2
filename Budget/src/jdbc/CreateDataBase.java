package jdbc;

import java.sql.*;

public class CreateDataBase {
    public static void main(String[] args) {
        String driver = "org.postgres.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "dima1234dumi";

        Connection connection = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "CREATE SCHEMA IF NOT EXISTS \"Users\";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "CREATE TABLE IF NOT EXISTS \"Users\".User ("
                + "  username varchar(15) NOT NULL PRIMARY KEY,"
                + "  email varchar(50) NOT NULL, "
                + "  password varchar(50) NOT NULL , "
                + "  reapeatPassword varchar(50) NOT NULL " + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String preparedSql = "INSERT INTO \"Users\".User (username, email, password, repeatPassword) "
                + "SELECT * FROM (SELECT ?, ?, ?, ?) AS tmp "
                + "WHERE NOT EXISTS (SELECT username FROM \"Users\".User "
                + "WHERE username = ?) LIMIT 1;";


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}