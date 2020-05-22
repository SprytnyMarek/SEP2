package server.dataaccess;

import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

public interface TransactionPane
{
  double getBudget(String username);
  void addToBudget(String username, double amount);
  ArrayList getStringUsername();
  void moneyTransfer(String username, String userToSend, double money, String text);
  ArrayList getStringCategories();
  ArrayList<SpendingsInfo> categoryTransfer(String username, String categoryToSend, double money);
}

