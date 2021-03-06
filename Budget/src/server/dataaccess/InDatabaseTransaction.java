package server.dataaccess;

import dao.accountDAO.AccountDAO;
import dao.accountDAO.AccountDAOImpl;
import dao.spendingsDAO.SpendingsDAO;
import dao.spendingsDAO.SpendingsDAOImpl;
import dao.transactionCategoriesDAO.TransactionCategoriesDAO;
import dao.transactionCategoriesDAO.TransactionCategoriesDAOImpl;
import dao.userDAO.UserDAO;
import dao.userDAO.UserDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.SpendingsInfo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class InDatabaseTransaction implements TransactionPane
{
  private PropertyChangeSupport support;

  public InDatabaseTransaction(){
    support = new PropertyChangeSupport(this);
  }

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

  /**
   * get all users' username
   * @return arraylist with all usernames
   */
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

  /**
   * subtracts from the user's balance sending, adds to the user's balance receiving,
   * adds the transaction to the database, fires an event to update the view with all transactions
   * @param username user who sends money
   * @param userToSend user who receives money
   * @param money amount of money
   * @param text description given by the user
   */
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
    try
    {
      SpendingsDAO dao = SpendingsDAOImpl.getInstance();
      dao.create(username, "Money transfer to "+userToSend, money);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    try
    {
      SpendingsDAO dao = SpendingsDAOImpl.getInstance();
      ArrayList<SpendingsInfo> spendingsInfos = dao.readByUsernameID(username);
      support.firePropertyChange("PopulateCategoryList", username, spendingsInfos);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  /**
   * get list of all categories
   * @return arraylist of all categories
   */
  @Override public ArrayList getStringCategories()
  {
    try
    {
      TransactionCategoriesDAO dao = TransactionCategoriesDAOImpl.getInstance();
      ArrayList categories = dao.getStringCategories();
      return categories;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

    return null;
  }

  /**
   * subtract from the user's balance, add category, amount of money and user transaction to the database
   * fires an event to update the view with all transactions
   * @param username of the user
   * @param categoryToSend the category the user chose
   * @param money amount of money
   */
  @Override public void categoryTransfer(String username, String categoryToSend, double money)
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
      SpendingsDAO dao = SpendingsDAOImpl.getInstance();
      dao.create(username, categoryToSend, money);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    try
    {
      SpendingsDAO dao = SpendingsDAOImpl.getInstance();
      ArrayList<SpendingsInfo> spendingsInfos = dao.readByUsernameID(username);
      support.firePropertyChange("PopulateCategoryList", username, spendingsInfos);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  /**
   * get all spendings of a user
   * @param username of user
   * @return arraylist with all spendings
   */
  @Override public ArrayList<SpendingsInfo> getSpendingsInfo(String username)
  {
    try
    {
      SpendingsDAO dao = SpendingsDAOImpl.getInstance();
      ArrayList<SpendingsInfo> spendingsInfos = dao.readByUsernameID(username);
      return spendingsInfos;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    if(null == name){
      addPropertyChangeListener(listener);
    }
    else {
      support.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    if(null == name){
      removePropertyChangeListener(listener);
    }
    else {
      support.removePropertyChangeListener(name, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
