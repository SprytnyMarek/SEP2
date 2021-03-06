package server.networking;

import javafx.beans.property.StringProperty;
import server.model.Model;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.TransactionInformation;
import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer, PropertyChangeListener
{
  private List<ClientCallBack> clientCallbacks;
  private Model model;

  public RMIServerImpl(Model model) throws RemoteException{
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
    clientCallbacks = new ArrayList<>();
    this.model.addPropertyChangeListener(this);
  }


  //returns login result
  @Override public String loginResult(User user)
  {
    return model.loginResult(user);
  }

  //returns register result
  @Override public String registerUser(User user)
  {
    return model.registerUser(user);
  }

  //adds client listener if he logs in
  @Override public void registerClient(ClientCallBack client)

  {
    clientCallbacks.add(client);
  }

  //removes client listener if he logs out
  @Override public void unregisterClient(ClientCallBack client)

  {
    clientCallbacks.remove(client);
  }

  @Override public double getBudget(String username)
  {
    return model.getBudget(username);
  }

  @Override public void addToBudget(String username, double amount)
  {
    model.addToBudget(username, amount);
  }

  @Override public ArrayList getStringUsernames()
  {
    return model.getStringUsername();
  }

  @Override public ArrayList getStringCategories()
  {
    return model.getStringCategories();
  }

  @Override public void moneyTransfer(String username, String userToSend, double money,
      String text)
  {
    model.moneyTransfer(username, userToSend, money, text);
  }

  @Override public void spendingsTransfer(String username, String categoryToSend, double amount) throws RemoteException
  {
    model.categoryTransfer(username, categoryToSend, amount);
  }


  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
      throws RemoteException
  {
    return model.getSpendingsInfo(username);
  }

  @Override public void addNotification(String username, String userToSend,
      double notificationAmount) throws RemoteException
  {
    model.addNotification(username, userToSend, notificationAmount);
  }

  @Override public ArrayList<Notification> getNotificationList(String username)
      throws RemoteException
  {
    return model.getNotificationList(username);
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if (propertyChangeEvent.getPropertyName().equals("TransferSent")) {
      for (ClientCallBack client : clientCallbacks) {
        try {
          if (client.getUsername().equals((String) propertyChangeEvent.getOldValue())) {
            client.updateBudgetOnSending((TransactionInformation) propertyChangeEvent.getNewValue());
          }
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    }
    if (propertyChangeEvent.getPropertyName().equals("TransferReceived")) {
      for (ClientCallBack client : clientCallbacks) {
        try {
          if (client.getUsername().equals((String) propertyChangeEvent.getOldValue())) {
            client.updateBudgetOnReceiving((TransactionInformation) propertyChangeEvent.getNewValue());
          }
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    }
    if(propertyChangeEvent.getPropertyName().equals("AddBudget")){
      for(ClientCallBack client: clientCallbacks){
        try
        {
          if(client.getUsername().equals((String)propertyChangeEvent.getOldValue())){
            client.updateBudget((double)propertyChangeEvent.getNewValue());
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    }
    if(propertyChangeEvent.getPropertyName().equals("PopulateCategoryList")){
      for(ClientCallBack client: clientCallbacks){
        try
        {
          if(client.getUsername().equals((String)propertyChangeEvent.getOldValue())){
              client.populateListView((ArrayList<SpendingsInfo>) propertyChangeEvent.getNewValue());
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    }
    if(propertyChangeEvent.getPropertyName().equals("UserAsking")){
      for(ClientCallBack client: clientCallbacks){
        try
        {
          if(client.getUsername().equals((String)propertyChangeEvent.getOldValue())){
            client.addInNotificationView((Notification) propertyChangeEvent.getNewValue());
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    }
    if(propertyChangeEvent.getPropertyName().equals("UserOwing")){
      for(ClientCallBack client: clientCallbacks){
        try
        {
          if(client.getUsername().equals((String)propertyChangeEvent.getOldValue())){
            client.addInNotificationView((Notification) propertyChangeEvent.getNewValue());
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
