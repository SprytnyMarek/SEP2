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
                + "  username varchar NOT NULL PRIMARY KEY,"
                + "  email varchar NOT NULL, "
                + "  password varchar NOT NULL );";

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
                + "  adminid varchar NOT NULL PRIMARY KEY,"
                + "  password varchar NOT NULL );";

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
                + "  username varchar NOT NULL REFERENCES \"SEP2\".users(username) PRIMARY KEY,"
                + "  balance numeric NOT NULL);";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populate account table
        sql = "INSERT INTO \"SEP2\".account(username, balance) VALUES('troels', 1337) ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".account(username, balance) VALUES('pawel', 5) ;";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".transactioncategories("
            + " title varchar NOT NULL PRIMARY KEY);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO \"SEP2\".transactioncategories(title) VALUES ( 'Groceries');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories( title) VALUES ( 'Clothing');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        sql = "INSERT INTO \"SEP2\".transactioncategories( title) VALUES ( 'Entertainment');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Electronics');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Traveling');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Furniture');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Drugs/medicine');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Bills');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories( title) VALUES ( 'Restaurants');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories( title) VALUES ( 'Transport');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Utilities');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories(  title) VALUES ( 'Health');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO \"SEP2\".transactioncategories( title) VALUES ( 'Services');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".transaction("
                + " id serial NOT NULL PRIMARY KEY,"
                + " username varchar NOT NULL REFERENCES \"SEP2\".users(username) , "
                + " title varchar NOT NULL ,"
                + " amountofmoney numeric NOT NULL); ";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO \"SEP2\".transaction( username, title, amountofmoney) VALUES ( 'troels','Clothing',100);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        sql = "CREATE TABLE IF NOT EXISTS \"SEP2\".notifications("
            + " id serial NOT NULL PRIMARY KEY, "
            + " usernameasking varchar NOT NULL REFERENCES \"SEP2\".users(username), "
            + " usernameowing varchar NOT NULL REFERENCES \"SEP2\".users(username), "
            + " amountofmoney numeric NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
