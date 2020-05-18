package jdbc;

import shared.datatransfer.Password;

import java.sql.*;

public class CreateDataBase {
    public static void main(String[] args) {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = Password.getPassword();

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

        //create users table
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

        //populate table users
        sql = "INSERT INTO \"SEP2\".users(username, email, password) VALUES('troels', 'troels@via.dk', '1234') ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //I don't know what this one does
        String preparedSql =
                "INSERT INTO \"SEP2\".users (username, email, password) "
                        + "SELECT * FROM (SELECT ?, ?, ?) AS tmp "
                        + "WHERE NOT EXISTS (SELECT username FROM \"SEP2\".users "
                        + "WHERE username = ?) LIMIT 1;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //create admin table
        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".admin ("
                + "  adminid varchar(15) NOT NULL PRIMARY KEY,"
                + "  password varchar(50) NOT NULL );";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populate table admin
        sql = "INSERT INTO \"SEP2\".admin(adminid, password) VALUES('supertroels', '1234') ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //create account table
        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".account ("
                + "  username varchar(15) NOT NULL PRIMARY KEY,"
                + "  balance numeric NOT NULL,"
                + "  fixedPayments numeric NOT NULL,"
                + "  fixedIncome numeric NOT NULL,"
                + "  totalPayments numeric NOT NULL,"
                + "  totalIncome numeric NOT NULL);";
        //+ "  timePeriod timestamp default current_timestamp);";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populate account table
        sql = "INSERT INTO \"SEP2\".account(username, balance, fixedPayments, fixedIncome, totalPayments, totalIncome) VALUES('troels', 1337, 100, 101, 102, 103) ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //make them FK
        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".transaction("
                + " adminLogIn varchar(15) NOT NULL , "
                + " username varchar(15) NOT NULL , "
                + " category_code varchar(10) NOT NULL ,"
                + " amountOfMoney numeric NOT NULL PRIMARY KEY, "
                //   + " date_of_transaction timestamp NOT NULL, "
                + " payments numeric(10) NOT NULL, "
                + " description char(10) );";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO \"SEP2\".transaction( adminLogIn, username, category_code, amountOfMoney, payments, description) VALUES ( 'troels0','troels1','Cloth',100, 101,'red');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
