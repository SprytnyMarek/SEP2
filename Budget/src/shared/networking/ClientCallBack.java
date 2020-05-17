package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote
{
  String getUsername() throws RemoteException;
  void updateBudget(double amount) throws RemoteException;
}
