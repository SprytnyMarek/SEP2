package server.dataaccess;

import shared.datatransfer.SpendingsInfo;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface TransactionPane extends PropertyChangeSubject
{
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsername();
  void moneyTransfer(String username, String userToSend, double money, String text);
  ArrayList getStringCategories();
  void categoryTransfer(String username, String categoryToSend, double money);
  ArrayList<SpendingsInfo> getSpendingsInfo(String username);
}

