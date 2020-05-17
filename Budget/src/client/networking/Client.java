package client.networking;

import shared.datatransfer.User;

public interface Client
{
  String loginResult(User user);
  String registerUser(User user);
  void unregisterUser();
  double getBudget(String username);
  String addToBudget(String username, double amount);
}
