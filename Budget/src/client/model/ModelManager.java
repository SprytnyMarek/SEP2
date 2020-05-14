package client.model;

import client.networking.Client;
import shared.datatransfer.User;

public class ModelManager implements Model
{
  private Client client;
  private User loggedInUser;
  private User registeredUser;

  public ModelManager(Client client)
  {
    this.client = client;
  }

  //gets result if login was successful
  @Override public String loginResult(String username, String password)
  {
    loggedInUser = new User(username, password);
    return client.loginResult(loggedInUser);
  }

  //gets result if register was successful
  @Override public String registerUser(String username, String email,
      String password, String repeatPassword)
  {
    registeredUser = new User(username, email, password, repeatPassword);
    return client.registerUser(registeredUser);
  }
}
