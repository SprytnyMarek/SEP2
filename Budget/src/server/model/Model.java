package server.model;

import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends PropertyChangeSubject
{
  String loginResult(User user);
  String registerUser(User user);
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsername();
  ArrayList getStringCategories();
  void moneyTransfer(String username, String userToSend, double money, String text);
  void categoryTransfer(String username, String categoryToSend, double money);
}
