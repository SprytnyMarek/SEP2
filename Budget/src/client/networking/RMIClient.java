package client.networking;


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

  //gets login result and if login is successful it registers user to listen to changes that happen in the server
  @Override public String loginResult(User user)
  {
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
    return "";
  }

  //gets register result
  @Override public String registerUser(User user)
  {
    try
    {
      return server.registerUser(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  @Override public void unregisterUser()
  {
    try
    {
      server.unregisterClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public double getBudget(String username)
  {
    try
    {
      return server.getBudget(username);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public void addToBudget(String username, double amount)
  {
    try
    {
      server.addToBudget(username, amount);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList getStringUsernames()
  {
    try
    {
      return server.getStringUsernames();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
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
