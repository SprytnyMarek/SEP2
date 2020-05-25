package client.networking;


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
  private RMIServer server;
  private PropertyChangeSupport support;
  private String username;

  public RMIClient(){
    support = new PropertyChangeSupport(this);
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)registry.lookup("Budget");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  public void access(){
    try
    {
      server.acquireWriteAccess();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

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
    access();
    try
    {
      if(server.loginResult(user).equals("OK")){
        server.registerClient(this);
        username = user.getUsername();
      }
      return server.loginResult(user);
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
    access();
    try
    {
      return server.registerUser(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
    return "";
  }

  @Override public void unregisterUser()
  {
    access();
    try
    {
      server.unregisterClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
  }

  @Override public double getBudget(String username)
  {
    access();
    try
    {
      return server.getBudget(username);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    release();
    return 0;
  }

  @Override public void addToBudget(String username, double amount)
  {
    access();
    try
    {
      server.addToBudget(username, amount);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    release();
  }

  @Override public ArrayList getStringUsernames()
  {
    access();
    try
    {
      return server.getStringUsernames();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
    return null;
  }

  @Override public ArrayList getStringCategories()
  {
    access();
    try
    {
      return server.getStringCategories();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
    return null;
  }

  @Override public void moneyTransfer(String username, String userToSend, double money,
      String text)
  {
    access();
    try
    {
      server.moneyTransfer(username, userToSend, money, text);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
  }

  @Override public void spendingsTransfer(String username, String category,
      double amount)
  {
    access();
    try
    {
      server.spendingsTransfer(username, category, amount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
  }

  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
  {
    access();
    try
    {
      return server.getSpendingsInfo(username);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    release();
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
