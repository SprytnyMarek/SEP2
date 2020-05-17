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

  @Override public void addToBudget(String username, double amount)
  {
    try
    {
      AccountDAO dao = AccountDAOImpl.getInstance();
      Account account = dao.readByUsernameID(username);
      account.setBalance(account.getBalance()+amount);
      dao.update(account);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
