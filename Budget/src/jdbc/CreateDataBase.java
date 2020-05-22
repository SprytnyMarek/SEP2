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

        sql = "INSERT INTO \"SEP2\".users(username, email, password) VALUES('pawel', 'pawel@via.dk', '1234') ;";

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
                + "  fixedpayments numeric NOT NULL,"
                + "  fixedincome numeric NOT NULL,"
                + "  totalpayments numeric NOT NULL,"
                + "  totalincome numeric NOT NULL);";
        //+ "  timePeriod timestamp default current_timestamp);";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populate account table
        sql = "INSERT INTO \"SEP2\".account(username, balance, fixedpayments, fixedincome, totalpayments, totalincome) VALUES('troels', 1337, 100, 101, 102, 103) ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".account(username, balance, fixedpayments, fixedincome, totalpayments, totalincome) VALUES('pawel', 5, 100, 101, 102, 103) ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //make them FK
        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".transaction("
                + " username varchar(15) NOT NULL , "
                + " categorycode varchar(10) NOT NULL ,"
                + " amountofmoney numeric NOT NULL PRIMARY KEY); ";
                //   + " date_of_transaction timestamp NOT NULL, "
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO \"SEP2\".transaction( username, categorycode, amountofmoney) VALUES ( 'troels','Clothing',100);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".transactionCategories("
            + " id numeric NOT NULL PRIMARY KEY, "
            + " title varchar(20) NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '1','Groceries');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '2','Clothing');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '3','Entertainment');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '4','Electronics');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '5','Traveling');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '6','Furniture');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '7','Drugs/medicine');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '8','Bills');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '9','Restaurants');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '10','Transport');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '11','Utilities');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '12','Health');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactionCategories( id, title) VALUES ( '13','Services');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
