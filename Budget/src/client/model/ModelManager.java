package client.model;

import client.networking.Client;
import javafx.beans.property.StringProperty;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
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
  private String categories;

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
    return budget;
  }

  @Override public void addToBudget(double amount)
  {
    client.addToBudget(username, amount);
  }

  @Override public ArrayList getAllUsernamesForAdmin(){
    ArrayList arrayList = client.getStringUsernames();
    return arrayList;
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

  @Override public ArrayList getStringCategories()
  {
    ArrayList arrayList = client.getStringCategories();
    for(int i = 0; i<arrayList.size(); i++){
      if(arrayList.get(i).equals(categories)){
        arrayList.remove(i);
      }
    }
    return arrayList;
  }

  /**
   * checks if the user has enough money in his balance to send money to the other user
   * @param userToSend user which will receive the money
   * @param money amount to be send
   * @param text description given by the user
   * @return a String that says if the transaction is successful or not
   */
  @Override public String moneyTransfer(String userToSend, double money,
      String text)
  {
    if(getBudget()<money){
      return "Not enough money";
    }
    else {
      client.moneyTransfer(username, userToSend, money, text);
      return "Success";
    }
  }

  @Override public String spendingsTransfer(String category, double amount)
  {
    if(getBudget()<amount){
      return "Not enough money";
    }
    else {
      client.spendingsTransfer(username, category, amount);
      return "Success";
    }
  }

  @Override public  ArrayList<SpendingsInfo> getInfoForAdmin(String userToGet){
    return client.getSpendingsInfo(userToGet);
  }

  @Override public ArrayList<SpendingsInfo> getSpendingsInfos()
  {
    return client.getSpendingsInfo(username);
  }

  @Override public ArrayList<Notification> getNotificationList()
  {
    return client.getNotificationList();
  }

  @Override public void addNotification(String userToSend,
      double notificationAmount)
  {
    client.addNotification(username, userToSend, notificationAmount);
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if(propertyChangeEvent.getPropertyName().equals("AddBudget")){
      support.firePropertyChange("AddBudget", null, propertyChangeEvent.getNewValue());
    }
    if(propertyChangeEvent.getPropertyName().equals("TransferSent")){
      support.firePropertyChange("TransferSent", null, propertyChangeEvent.getNewValue());
    }
    if(propertyChangeEvent.getPropertyName().equals("TransferReceived")){
      support.firePropertyChange("TransferReceived", null, propertyChangeEvent.getNewValue());
    }
    if(propertyChangeEvent.getPropertyName().equals("PopulateCategoryList")){
      support.firePropertyChange("PopulateCategoryList", null, propertyChangeEvent.getNewValue());
    }
    if(propertyChangeEvent.getPropertyName().equals("AddInNotificationList")){
      support.firePropertyChange("AddInNotificationList", null, propertyChangeEvent.getNewValue());
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
