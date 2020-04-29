package server;

import server.model.Model;
import server.model.ModelManager;
import server.networking.RMIServerImpl;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args) throws RemoteException
  {
    RMIServerImpl sv = new RMIServerImpl();
    try
    {
      sv.startServer();
    }
    catch (IOException | AlreadyBoundException e)
    {
      System.out.println("Failure");
    }
  }
}
