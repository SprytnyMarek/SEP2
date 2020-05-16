package client.model;

import client.networking.Client;
import shared.datatransfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private Client client;
  private User loggedInUser;
  private User registeredUser;
  private PropertyChangeSupport support;

  public ModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  //gets result if login was successful
  @Override public String loginResult(String username, String password)
  {
    loggedInUser = new User(username, password);
    return client.loginResult(loggedInUser);
  }

  //gets result if register was successful
  @Override public String registerUser(String username, String email,
      String password, String repeatPassword)
  {
    registeredUser = new User(username, email, password, repeatPassword);
    return client.registerUser(registeredUser);
  }

  @Override public void unregisterUser()
  {
    client.unregisterUser();
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

  //TODO connect with gui , get username of person (somewhere store the username for future use)
  @Override public double getBudget(String username)
  {
    return client.getBudget(username);
  }

  //TODO connect with gui , if return "OK" then clear the field for add money , if return "The amount is invalid" put it in label
  /*@Override public String addToBudget(String username, int amount)
  {
    //return client.addToBudget(username, amount);
    return "";
  }*/
}
