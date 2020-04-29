package server.networking;

import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  public RMIServerImpl() throws RemoteException{
    UnicastRemoteObject.exportObject(this, 0);
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Budget", this);
  }

  @Override public String loginResult(User user)
  {
    return null;
  }

  @Override public String registerUser(User user)
  {
    return null;
  }

  @Override public void registerClient(ClientCallBack client)

  {

  }

  @Override public void unregisterClient(ClientCallBack client)

  {

  }
}
