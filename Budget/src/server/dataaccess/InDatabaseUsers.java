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
      if(user.getUsername().equals("") || user.getUsername() == null){
        result = "Username field cannot be empty";
      }
      else if(user.getEmail().equals("") || user.getEmail() == null){
        result = "Email field cannot be empty";
      }
      else if(user.getPassword().equals("") || user.getPassword() == null){
        result = "Password field cannot be empty";
      }
      else if(!(user.getPassword().equals(user.getRepeatPassword()))){
        result = "You did not input the same password";
      }
      else {
        result = "OK";
        //TODO register person to database
      }
    }
    return result;
  }
}
