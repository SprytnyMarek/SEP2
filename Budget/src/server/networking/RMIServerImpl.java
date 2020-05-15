package server.networking;

import server.model.Model;
import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  private List<ClientCallBack> clientCallbacks;
  private Model model;

  public RMIServerImpl(Model model) throws RemoteException{
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
    clientCallbacks = new ArrayList<>();
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Budget", this);
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

  @Override public int getBudget(String username)
  {
    return model.getBudget(username);
  }

  @Override public String addToBudget(String username, int amount)
  {
    return model.addToBudget(username, amount);
  }
}
