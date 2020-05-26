package server.model;

import server.dataaccess.Notifications;
import server.dataaccess.TransactionPane;
import server.dataaccess.UserHome;
import shared.datatransfer.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private UserHome userHome;
  private TransactionPane transactionPane;
  private Notifications notifications;
  private PropertyChangeSupport support;

  public ModelManager(UserHome userHome, TransactionPane transactionPane, Notifications notifications){
    this.userHome = userHome;
    this.transactionPane = transactionPane;
    this.notifications = notifications;
    support = new PropertyChangeSupport(this);
    this.transactionPane.addPropertyChangeListener(this::propertyChange);
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



  @Override public double getBudget(String username)
  {
    return transactionPane.getBudget(username);
  }

  @Override public void addToBudget(String username, double amount)
  {
    support.firePropertyChange("AddBudget", username, amount);
    transactionPane.addToBudget(username,amount);
  }

  @Override public ArrayList getStringUsername()
  {
    return transactionPane.getStringUsername();
  }

  @Override public ArrayList getStringCategories()
  {
    return transactionPane.getStringCategories();
  }

  @Override
  public void moneyTransfer(String username, String userToSend, double money,
      String text) {
    TransactionInformation transactionInformation = new TransactionInformation(text, money);
    support.firePropertyChange("TransferSent", username, transactionInformation);
    support.firePropertyChange("TransferReceived", userToSend, transactionInformation);
    transactionPane.moneyTransfer(username, userToSend, money, text);
  }

  @Override public void categoryTransfer(String username, String categoryToSend,
      double money)
  {
    transactionPane.categoryTransfer(username, categoryToSend, money);
  }

  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
  {
    return transactionPane.getSpendingsInfo(username);
  }

  @Override public void addNotification(String usernameAsking,
      String usernameOwing, double amount)
  {
    Notification notification = new Notification(usernameAsking, usernameOwing, amount);
    support.firePropertyChange("UserAsking", usernameAsking, notification);
    support.firePropertyChange("UserOwing", usernameOwing, notification);
    notifications.addNotification(usernameAsking, usernameOwing, amount);
  }

  @Override public ArrayList<Notification> getNotificationList(String username)
  {
    return notifications.getNotificationList(username);
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if(propertyChangeEvent.getPropertyName().equals("PopulateCategoryList")){
      support.firePropertyChange("PopulateCategoryList", propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
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
