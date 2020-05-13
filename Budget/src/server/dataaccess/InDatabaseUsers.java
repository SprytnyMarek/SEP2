package server.dataaccess;

import dao.UserDAO;
import dao.UserDAOImpl;
import shared.datatransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InDatabaseUsers implements UserHome {
    private List<User> users;

    public InDatabaseUsers() {
        users = new ArrayList<>();
    }

    //gets from database user that has the same username and checks if username and password match
    @Override
    public String loginResult(User user) {
        try {
            UserDAO dao = UserDAOImpl.getInstance();
            User result = dao.readByUsername(user.getUsername());
            if (result != null) {
                if (result.getPassword().equals(user.getPassword())) {
                    return "OK";
                } else {
                    return "Password incorrect";
                }
            }
            return "User not found"; // + password


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Could not connect to database";


    }


    //gets user from database to see if there exist a user with the same username
    //if the info are valid the user is saved in database
    @Override
    public String registerUser(User user) {
        String result = "";
        User alreadyInDatabase = null;
        try {
            UserDAO dao = UserDAOImpl.getInstance();
            alreadyInDatabase = dao.readByUsername(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (alreadyInDatabase != null) {
            result = "There is already a user with this name";
        }
        if (!(result.equals("There is already a user with this name"))) {
            if (user.getUsername() == null || user.getUsername().equals("") || !(user.getUsername().matches("[a-zA-Z]+")) || user.getUsername().length() < 3 || user.getUsername().length() > 12) {
                result = "Invalid username";
            } else if (user.getEmail() == null || user.getEmail().equals("") || user.getEmail().length() < 1 || user.getEmail().length() > 13 || !(user.getEmail().matches("[a-zA-Z0-9.\\-_]+")) || !(user.getEmail().matches("[a-zA-Z].*"))) {
                result = "Invalid email";
            } else if (user.getPassword() == null || user.getPassword().equals("") || user.getPassword().length() < 4 || user.getPassword().length() > 14) {
                result = "Invalid password";
            } else if (!(user.getPassword().equals(user.getRepeatPassword()))) {
                result = "Passwords do not match";
            } else {
                result = "OK";
                user.setEmail(user.getEmail() + "@via.dk");
                try {
                    UserDAO dao = UserDAOImpl.getInstance();
                    dao.create(user.getUsername(), user.getEmail(), user.getPassword(), user.getRepeatPassword());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
