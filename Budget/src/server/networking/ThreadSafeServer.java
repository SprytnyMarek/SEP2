package server.networking;

import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.User;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ThreadSafeServer implements ServerAccess
{
  private boolean activeWriter;
  private int waitingWriters;
  private int activeReaders;
  private RMIServer serverImpl;

  public ThreadSafeServer(RMIServer serverImpl) throws RemoteException{
    UnicastRemoteObject.exportObject(this, 0);
    this.serverImpl = serverImpl;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Budget", this);
  }

  /**
   * users gain access one at a time, checks if there is another user already in the database
   * @return the class RMIServer
   */
  public synchronized RMIServer acquireWriteAccess()
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
    return serverImpl;
  }

  /**
   * releases the user so another user can gain access
   */
  public synchronized void releaseWriteAccess()
  {
    activeWriter = false;
    notifyAll();
  }


}
