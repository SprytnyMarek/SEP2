package server.dataaccess;

public interface TransactionPane
{
  double getBudget(String username);
  String addToBudget(String username, double amount);
}
