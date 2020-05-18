package server.dataaccess;

import dao.accountDAO.AccountDAO;
import dao.accountDAO.AccountDAOImpl;
import dao.userDAO.UserDAO;
import dao.userDAO.UserDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.User;

import java.sql.SQLException;
import java.util.ArrayList;

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

  @Override public ArrayList getStringUsername()
  {
    try
    {
      UserDAO dao = UserDAOImpl.getInstance();
      ArrayList usernames = dao.getStringUsernames();
      return usernames;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

    return null;
  }

  @Override public void moneyTransfer(String username, String userToSend, double money,
      String text)
  {
    try
    {
      AccountDAO dao = AccountDAOImpl.getInstance();
      Account account = dao.readByUsernameID(username);
      account.setBalance(account.getBalance()-money);
      dao.update(account);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    try
    {
      AccountDAO dao = AccountDAOImpl.getInstance();
      Account account = dao.readByUsernameID(userToSend);
      account.setBalance(account.getBalance()+money);
      dao.update(account);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
