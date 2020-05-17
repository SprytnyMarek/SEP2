package client.networking;

import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  String loginResult(User user);
  String registerUser(User user);
  void unregisterUser();
  double getBudget(String username);
  void addToBudget(String username, double amount);
}
