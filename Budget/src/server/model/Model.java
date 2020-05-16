package server.model;

import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

public interface Model extends PropertyChangeSubject
{
  String loginResult(User user);
  String registerUser(User user);
  double getBudget(String username);
  //String addToBudget(String username, int amount);
}
