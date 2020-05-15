package server.dataaccess;

public interface TransactionPane
{
  int getBudget(String username);
  String addToBudget(String username, int amount);
}
