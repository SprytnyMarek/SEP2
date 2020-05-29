package client.networking;


import javafx.beans.property.StringProperty;
import server.networking.ServerAccess;
import server.networking.ThreadSafeServer;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.TransactionInformation;
import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements Client, ClientCallBack
{
  private ServerAccess server;
  private PropertyChangeSupport support;
  private String username;

  public RMIClient(){
    support = new PropertyChangeSupport(this);
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (ServerAccess)registry.lookup("Budget");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * if the user gains access to the server he will be able to access the methods in RMIServer
   * @return the class RMIServer
   */
  public RMIServer access(){
    try
    {
      RMIServer serverImlp = server.acquireWriteAccess();
      return serverImlp;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * releases the server so another user can gain access
   */
  public void release(){
    try
    {
      server.releaseWriteAccess();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  //gets login result and if login is successful it registers user to listen to changes that happen in the server
  @Override public String loginResult(User user)
  {
    RMIServer serverImlp = access();
    try
    {
      if(serverImlp.loginResult(user).equals("OK")){
        serverImlp.registerClient(this);
        username = user.getUsername();
      }
      return serverImlp.loginResult(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }
    return "";
  }

  //gets register result
  @Override public String registerUser(User user)
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.registerUser(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return "";
  }

  @Override public void unregisterUser()
  {
    RMIServer serverImlp = access();
    try
    {
      serverImlp.unregisterClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

  }

  @Override public double getBudget(String username)
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.getBudget(username);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return 0;
  }

  @Override public void addToBudget(String username, double amount)
  {
    RMIServer serverImlp = access();
    try
    {
      serverImlp.addToBudget(username, amount);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

  }

  @Override public ArrayList getStringUsernames()
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.getStringUsernames();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return null;
  }

  @Override public ArrayList getStringCategories()
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.getStringCategories();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return null;
  }

  @Override public void moneyTransfer(String username, String userToSend, double money,
      String text)
  {
    RMIServer serverImlp = access();
    try
    {
      serverImlp.moneyTransfer(username, userToSend, money, text);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

  }

  @Override public void spendingsTransfer(String username, String category,
      double amount)
  {
    RMIServer serverImlp = access();
    try
    {
      serverImlp.spendingsTransfer(username, category, amount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

  }

  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.getSpendingsInfo(username);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return null;
  }

  @Override public void addNotification(String username, String userToSend,
      double notificationAmount)
  {
    RMIServer serverImlp = access();
    try
    {
      serverImlp.addNotification(username, userToSend, notificationAmount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }
  }

  @Override public ArrayList<Notification> getNotificationList()
  {
    RMIServer serverImlp = access();
    try
    {
      return serverImlp.getNotificationList(username);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    finally
    {
      release();
    }

    return null;
  }

  @Override public String getUsername()
  {
    return username;
  }

  @Override public void updateBudget(double amount)
  {
    support.firePropertyChange("AddBudget", null, amount);
  }

  @Override
  public void updateBudgetOnSending(TransactionInformation transaction_sending) throws RemoteException {
    support.firePropertyChange("TransferSent", null, transaction_sending);
  }

  @Override
  public void updateBudgetOnReceiving(TransactionInformation transaction_receiving) throws RemoteException {
    support.firePropertyChange("TransferReceived", null, transaction_receiving);
  }

  @Override public void populateListView(ArrayList<SpendingsInfo> spendingsInfos)
      throws RemoteException
  {
    support.firePropertyChange("PopulateCategoryList", null, spendingsInfos);
  }

  @Override public void addInNotificationView(Notification notification)
  {
    support.firePropertyChange("AddInNotificationList", null, notification);
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
