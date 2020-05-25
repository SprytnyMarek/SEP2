package server;

import server.dataaccess.*;
import server.model.Model;
import server.model.ModelManager;
import server.networking.RMIServerImpl;
import server.networking.ThreadSafeServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args) throws RemoteException
  {
    UserHome userHome = new InDatabaseUsers();
    TransactionPane transactionPane = new InDatabaseTransaction();
    Notifications notifications = new InDatabaseNotifications();
    Model model = new ModelManager(userHome, transactionPane, notifications);
    RMIServerImpl sv = new RMIServerImpl(model);
    ThreadSafeServer tss = new ThreadSafeServer(sv);
    try
    {
      tss.startServer();
    }
    catch (IOException | AlreadyBoundException e)
    {
      System.out.println("Failure");
    }
  }
}
