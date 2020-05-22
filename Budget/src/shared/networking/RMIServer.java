package shared.networking;

import shared.datatransfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  String loginResult(User user) throws RemoteException;
  String registerUser(User user) throws RemoteException;
  void registerClient(ClientCallBack client) throws RemoteException;
  void unregisterClient(ClientCallBack client) throws RemoteException;
  double getBudget(String username) throws RemoteException;
  void addToBudget(String username, double amount) throws RemoteException;
  ArrayList getStringUsernames() throws RemoteException;
  ArrayList getStringCategories() throws RemoteException;
  void moneyTransfer(String username, String userToSend, double money, String text) throws RemoteException;
  void spendingsTransfer(String username, String categoryToSend, double amount) throws RemoteException;
}
