package server.dataaccess;

import shared.datatransfer.User;

import java.util.ArrayList;
import java.util.List;

public class InDatabaseUsers implements UserHome
{
  private List<User> users;

  public InDatabaseUsers(){
    users = new ArrayList<>();
    users.add(new User("Troels", "troels@gmail.com", "123", "123"));

  }

  @Override public String loginResult(User user)
  {
    String result = "User not found";
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
    return result;
  }

  @Override public String registerUser(User user)
  {
    String result = "";
    for(User u:users){
      if(u.getUsername().equals(user.getUsername())){
        result = "There is already a user with this name";
        break;
      }
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
        //TODO register person to database
      }
    }
    return result;
  }
}
