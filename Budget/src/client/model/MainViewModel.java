package client.model;

import javafx.beans.property.StringProperty;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface MainViewModel extends PropertyChangeSubject, PropertyChangeListener
{
  double getBudget();
  void addToBudget(double amount);
  String spendingsTransfer(String category, double amount);
  ArrayList getStringUsernames();
  ArrayList getStringCategories();
  String moneyTransfer(String userToSend, double money, String text);
  ArrayList<SpendingsInfo> getSpendingsInfos();
  ArrayList<Notification> getNotificationList();
  void addNotification(String userToSend, double notificationAmount);
}
