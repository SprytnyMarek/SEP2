package server.model;

import server.dataaccess.TransactionPane;
import server.dataaccess.UserHome;
import shared.datatransfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private UserHome userHome;
  private TransactionPane transactionPane;
  private PropertyChangeSupport support;

  public ModelManager(UserHome userHome, TransactionPane transactionPane){
    this.userHome = userHome;
    this.transactionPane = transactionPane;
    support = new PropertyChangeSupport(this);
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

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    if(null == name){
      addPropertyChangeListener(listener);
    }
    else {
      support.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    if(null == name){
      removePropertyChangeListener(listener);
    }
    else {
      support.removePropertyChangeListener(name, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public double getBudget(String username)
  {
    return transactionPane.getBudget(username);
  }

  /*
  @Override public String addToBudget(String username, int amount)
  {
    return transactionPane.addToBudget(username,amount);
  }*/
}
