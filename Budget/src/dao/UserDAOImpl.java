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
        return DriverManager.getConnection("jdbc:postgres://localhost:5432/postgres?currentSchema=jdbc", "postgres", "dima1234dumi");
    }

    public User create(String username, String email, String password, String repeatPassword) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User(username, email, password, repeatPassword) VALUES(?, ?, ?, ?) ;");
            statement.setString(1, username );
            statement.setString(2,email);
            statement.setString(3,password);
            statement.setString(4,repeatPassword);
            statement.executeUpdate();
            return  new User(username, email, password, repeatPassword);
        }
    }

    public List<User> readByUsername(String searchString) throws SQLException{
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE username = ? ");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> result = new ArrayList<>();
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String repeatPassword = resultSet.getString("repeatPassword");
                User user = new User(username,email,password,repeatPassword);
                result.add(user);
            }
        return result;
        }
    }


    @Override
    public void update(User user) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET username=?, email =?, passowrd=?, repeatPassword=? WHERE username =?");
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE username = ?");
            statement.setString(1,user.getUsername());
            statement.executeUpdate();
        }
    }
}
