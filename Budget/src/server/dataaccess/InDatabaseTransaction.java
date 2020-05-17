package server.dataaccess;

import dao.accountDAO.AccountDAO;
import dao.accountDAO.AccountDAOImpl;
import dao.userDAO.UserDAO;
import dao.userDAO.UserDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.User;

import java.sql.SQLException;

public class InDatabaseTransaction implements TransactionPane
{
  @Override public double getBudget(String username)
  {
    try
    {
      AccountDAO dao = AccountDAOImpl.getInstance();
      Account account = dao.readByUsernameID(username);
      return account.getBalance();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return 0;
  }

  @Override public String addToBudget(String username, double amount)
  {
    if(amount<=0){
      return "The amount is invalid";
    }
    try
    {
      AccountDAO dao = AccountDAOImpl.getInstance();
      Account account = dao.readByUsernameID(username);
      account.setBalance(account.getBalance()+amount);
      dao.update(account);
      return "OK";
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return "";
  }
}
