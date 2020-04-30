package server.model;

import server.dataaccess.UserHome;
import shared.datatransfer.User;

public class ModelManager implements Model
{
  private UserHome userHome;

  public ModelManager(UserHome userHome){
    this.userHome = userHome;
  }
  @Override public String loginResult(User user)
  {
    return userHome.loginResult(user);
  }
}
