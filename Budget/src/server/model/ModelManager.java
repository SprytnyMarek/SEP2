package server.model;

import server.dataaccess.TransactionPane;
import server.dataaccess.UserHome;
import shared.datatransfer.User;

public class ModelManager implements Model
{
  private UserHome userHome;
  private TransactionPane transactionPane;

  public ModelManager(UserHome userHome, TransactionPane transactionPane){
    this.userHome = userHome;
    this.transactionPane = transactionPane;
  }

  //returns login result
  @Override public String loginResult(User user)
  {
    return userHome.loginResult(user);
  }

  //returns register result
  @Override public String registerUser(User user)
  {
    return userHome.registerUser(user);
  }

  /*@Override public int getBudget(String username)
  {
    return transactionPane.getBudget(username);
  }

  @Override public String addToBudget(String username, int amount)
  {
    return transactionPane.addToBudget(username,amount);
  }*/
}
