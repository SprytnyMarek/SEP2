package client.model;

import shared.util.PropertyChangeSubject;

public interface Model extends PropertyChangeSubject
{
  String loginResult(String username, String password);
  String registerUser(String username, String email, String password, String repeatPassword);
  void unregisterUser();
  //int getBudget(String username);
  //String addToBudget(String username, int amount);
}
