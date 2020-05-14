package client.networking;


import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client, ClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;

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
}
