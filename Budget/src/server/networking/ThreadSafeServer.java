package server.networking;

import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ThreadSafeServer implements RMIServer
{
  private boolean activeWriter;
  private int waitingWriters;
  private int activeReaders;

  private RMIServer rmiServer;
  public ThreadSafeServer(RMIServer rmiServer){
    this.rmiServer = rmiServer;
  }

  @Override public synchronized void acquireWriteAccess()
  {
    waitingWriters++;
    while (activeWriter ){
      try
      {
        wait();
      }
      catch (InterruptedException e)
      { }
    }
    waitingWriters--;
    activeWriter = true;
  }


  @Override public synchronized void releaseWriteAccess()
  {
    activeWriter = false;
    notifyAll();
  }

  @Override public String loginResult(User user) throws RemoteException
  {
    return rmiServer.loginResult(user);
  }

  @Override public String registerUser(User user) throws RemoteException
  {
    return rmiServer.registerUser(user);
  }

  @Override public void registerClient(ClientCallBack client)
      throws RemoteException
  {
    rmiServer.registerClient(client);
  }

  @Override public void unregisterClient(ClientCallBack client)
      throws RemoteException
  {
    rmiServer.unregisterClient(client);
  }

  @Override public double getBudget(String username) throws RemoteException
  {
    return rmiServer.getBudget(username);
  }

  @Override public void addToBudget(String username, double amount)
      throws RemoteException
  {
    rmiServer.addToBudget(username, amount);
  }

  @Override public ArrayList getStringUsernames() throws RemoteException
  {
    return rmiServer.getStringUsernames();
  }

  @Override public ArrayList getStringCategories() throws RemoteException
  {
    return rmiServer.getStringCategories();
  }

  @Override public void moneyTransfer(String username, String userToSend,
      double money, String text) throws RemoteException
  {
    rmiServer.moneyTransfer(username, userToSend, money, text);
  }

  @Override public void spendingsTransfer(String username,
      String categoryToSend, double amount) throws RemoteException
  {
    rmiServer.spendingsTransfer(username, categoryToSend, amount);
  }

  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
      throws RemoteException
  {
    return rmiServer.getSpendingsInfo(username);
  }
}
