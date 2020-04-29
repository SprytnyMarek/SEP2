package client.networking;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client, ClientCallBack
{
  private RMIServer server;

  public RMIClient(){
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
}
