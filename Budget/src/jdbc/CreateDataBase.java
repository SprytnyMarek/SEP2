package jdbc;

import java.sql.*;

public class CreateDataBase {
    public static void main(String[] args) {
        String driver = "org.postgresql.Driver";
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

        String sql = "CREATE SCHEMA IF NOT EXISTS \"SEP2\";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".users ("
                + "  username varchar(15) NOT NULL PRIMARY KEY,"
                + "  email varchar(50) NOT NULL, "
                + "  password varchar(50) NOT NULL );";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".users(username, email, password) VALUES('troels', 'troels@via.dk', '1234') ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String preparedSql = "INSERT INTO \"SEP2\".users (username, email, password) "
                + "SELECT * FROM (SELECT ?, ?, ?) AS tmp "
                + "WHERE NOT EXISTS (SELECT username FROM \"SEP2\".users "
                + "WHERE username = ?) LIMIT 1;";


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
