package server.dataaccess;

import java.util.ArrayList;

public interface TransactionPane
{
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsername();
  void moneyTransfer(String username, String userToSend, double money, String text);
}
