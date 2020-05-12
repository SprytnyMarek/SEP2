package dao;

import shared.datatransfer.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;


    private UserDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }



    public static synchronized UserDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=\"SEP2\"", "postgres", "Petunia123");
    }

    public User create(String username, String email, String password, String repeatPassword) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(username, email, password) VALUES(?, ?, ?) ;");
            statement.setString(1, username );
            statement.setString(2,email);
            statement.setString(3,password);
            statement.executeUpdate();
            return  new User(username, email, password, repeatPassword);
        }
    }

    //TODO maybe find a better name
    public User readByUsername(String searchUsername) throws SQLException{
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? ");
            statement.setString(1,searchUsername );
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String repeatPassword = resultSet.getString("password");
                User user = new User(username,email,password,repeatPassword);
                return user;
            }
            else {
                return null;
            }
        }
    }


    @Override
    public void update(User user) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username=?, email =?, passowrd=?, repeatpassword=? WHERE username =?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRepeatPassword());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1,user.getUsername());
            statement.executeUpdate();
        }
    }
}
