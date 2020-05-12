package server.dataaccess;

import dao.UserDAO;
import dao.UserDAOImpl;
import shared.datatransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InDatabaseUsers implements UserHome
{
  private List<User> users;

  public InDatabaseUsers()
  {
    users = new ArrayList<>();
  }

  @Override public String loginResult(User user)
  {
    try {
      UserDAO dao = UserDAOImpl.getInstance();
      User result = dao.readByUsername(user.getUsername());
      if(result != null)
      {
        return  "OK";
      }
      return "Password incorrect";
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "Could not connect to database";
 /*   String result = "User not found";
    for(User u:users){
      if(u.getUsername().equals(user.getUsername())){
        if(u.getPassword().equals(user.getPassword())){
          result = "OK";
        }
        else {
          result = "Password incorrect";
        }
        break;
      }
    }
    return result; */

  }

  @Override public String registerUser(User user)
  {
    String result = "";
    User alreadyInDatabase = null;
    try
    {
      UserDAO dao = UserDAOImpl.getInstance();
      alreadyInDatabase = dao.readByUsername(user.getUsername());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    if(alreadyInDatabase!=null){
        result = "There is already a user with this name";
    }
    if(!(result.equals("There is already a user with this name"))){
      if(user.getUsername() == null || user.getUsername().equals("") || !(user.getUsername().matches("[a-zA-Z]+")) || user.getUsername().length()<3 || user.getUsername().length()>12){
        result = "Invalid username";
      }
      else if(user.getEmail() == null || user.getEmail().equals("") || user.getEmail().length()<1 || user.getEmail().length()>13 || !(user.getEmail().matches("[a-zA-Z0-9.\\-_]+")) || !(user.getEmail().matches("[a-zA-Z].*"))){
        result = "Invalid email";
      }
      else if(user.getPassword() == null || user.getPassword().equals("") || user.getPassword().length()<4 || user.getPassword().length()>14){
        result = "Invalid password";
      }
      else if(!(user.getPassword().equals(user.getRepeatPassword()))){
        result = "Passwords do not match";
      }
      else {
        result = "OK";
        user.setEmail(user.getEmail() + "@via.dk");
        try
        {
          UserDAO dao = UserDAOImpl.getInstance();
          dao.create(user.getUsername(), user.getEmail(), user.getPassword(), user.getRepeatPassword());
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
    return result;
  }
}
