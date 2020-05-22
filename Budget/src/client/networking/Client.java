package client.networking;

import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface Client extends PropertyChangeSubject
{
  String loginResult(User user);
  String registerUser(User user);
  void unregisterUser();
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsernames();
  ArrayList getStringCategories();
  void moneyTransfer(String username, String userToSend, double money, String text);
  void spendingsTransfer(String username, String category, double amount);
}
