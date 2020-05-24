package client.core;

import client.networking.Client;
import client.networking.RMIClient;
import shared.networking.RMIServer;

public class ClientFactory
{
  private Client client;

  public Client getClient(){
    if(client == null){
      client = new RMIClient();
    }
    return client;
  }
}
