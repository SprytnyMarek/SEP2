package shared.networking;

import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.TransactionInformation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientCallBack extends Remote {
    String getUsername() throws RemoteException;

    void updateBudget(double amount) throws RemoteException;

    void updateBudgetOnSending(TransactionInformation transaction_sending) throws RemoteException;

    void updateBudgetOnReceiving(TransactionInformation transaction_receiving) throws RemoteException;

  void populateListView(ArrayList<SpendingsInfo> spendingsInfos) throws RemoteException;
  void addInNotificationView(Notification notification) throws RemoteException;
}
