package shared.networking;

import shared.datatransfer.TransactionInformation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote {
    String getUsername() throws RemoteException;

    void updateBudget(double amount) throws RemoteException;

    void updateBudgetOnSending(TransactionInformation transaction_sending) throws RemoteException;

    void updateBudgetOnReceiving(TransactionInformation transaction_receiving) throws RemoteException;

}
