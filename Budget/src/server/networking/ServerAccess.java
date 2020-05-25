package server.networking;

import shared.networking.RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerAccess extends Remote
{

  RMIServer acquireWriteAccess() throws RemoteException;
  void releaseWriteAccess() throws RemoteException;
}
