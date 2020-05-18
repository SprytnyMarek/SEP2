package client.model;

import client.networking.Client;
import shared.datatransfer.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private Client client;
  private User loggedInUser;
  private User registeredUser;
  private PropertyChangeSupport support;
  private String username;
  private double balance;

  public ModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    this.client.addPropertyChangeListener(this);
  }

  //gets result if login was successful
  @Override public String loginResult(String username, String password)
  {
    this.username = username;
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

  @Override public double getBudget()
  {
    double budget = client.getBudget(username);
    balance = budget;
    return budget;
  }

  @Override public void addToBudget(double amount)
  {
    client.addToBudget(username, amount);
  }

  @Override public ArrayList getStringUsernames()
  {
    ArrayList arrayList = client.getStringUsernames();
    for(int i = 0; i<arrayList.size(); i++){
      if(arrayList.get(i).equals(username)){
        arrayList.remove(i);
      }
    }
    return arrayList;
  }

  @Override public String moneyTransfer(String userToSend, double money,
      String text)
  {
    if(balance<money){
      return "Not enough money";
    }
    else {
      client.moneyTransfer(username, userToSend, money, text);
      return "Success";
    }
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if(propertyChangeEvent.getPropertyName().equals("AddBudget")){
      support.firePropertyChange("AddBudget", null, propertyChangeEvent.getNewValue());
    }
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


}
