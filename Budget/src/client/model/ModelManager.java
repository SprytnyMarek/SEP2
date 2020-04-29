package client.model;

import client.networking.Client;

public class ModelManager implements Model
{
  private Client client;
  public ModelManager(Client client)
  {
    this.client = client;
  }
}
