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
}
