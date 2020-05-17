package server.dataaccess;

public interface TransactionPane
{
  double getBudget(String username);
  void addToBudget(String username, double amount);
}
