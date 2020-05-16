package shared.networking;

import shared.datatransfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  String loginResult(User user) throws RemoteException;
  String registerUser(User user) throws RemoteException;
  void registerClient(ClientCallBack client) throws RemoteException;
  void unregisterClient(ClientCallBack client) throws RemoteException;
  double getBudget(String username) throws RemoteException;
  //String addToBudget(String username, int amount);
}
