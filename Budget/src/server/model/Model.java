package server.model;

import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
import shared.datatransfer.User;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model extends PropertyChangeSubject, PropertyChangeListener
{
  String loginResult(User user);
  String registerUser(User user);
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsername();
  ArrayList getStringCategories();
  void moneyTransfer(String username, String userToSend, double money, String text);
  void categoryTransfer(String username, String categoryToSend, double money);
  ArrayList<SpendingsInfo> getSpendingsInfo(String username);
  void addNotification(String usernameAsking, String usernameOwing, double amount);
  ArrayList<Notification> getNotificationList(String username);
}
