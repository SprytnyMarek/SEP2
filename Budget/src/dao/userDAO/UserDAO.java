package dao.userDAO;

import shared.datatransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
    User create(String username, String email, String password, String repeatPassword) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    User readByUsername(String searchString) throws  SQLException;
    User readByUsernameAdmin(String searchString) throws SQLException;
    ArrayList getStringUsernames() throws SQLException;
    int getUsernameCount() throws SQLException;
}
