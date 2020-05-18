package client.model;

import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model extends PropertyChangeSubject, PropertyChangeListener
{
  String loginResult(String username, String password);
  String registerUser(String username, String email, String password, String repeatPassword);
  void unregisterUser();
  double getBudget();
  void addToBudget(double amount);
  ArrayList getStringUsernames();
  String moneyTransfer(String userToSend, double money, String text);
}
