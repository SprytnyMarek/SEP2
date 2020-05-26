package dao.accountDAO;

import dao.userDAO.UserDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.Password;
import shared.datatransfer.User;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO
{

  private static AccountDAOImpl instance;

  //make private to prevent creating different DAO classes
  private AccountDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  //makes instance so every client access the same file
  public static synchronized AccountDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new AccountDAOImpl();
    }
    return instance;
  }

  //Do we have to create connections between db and dao every time?
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"SEP2\"",
            "postgres", Password.getPassword());
  }

  @Override public Account create(String username, double balance) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO account(username, balance) VALUES(?, ?) ;");
      statement.setString(1, username);
      statement.setDouble(2, balance);
      statement.executeUpdate();
      return new Account(username, balance);
    }
  }

  @Override public void update(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE account SET username=?, balance =? WHERE username =?");
      statement.setString(1, account.getUsername());
      statement.setDouble(2, account.getBalance());
      statement.setString(3, account.getUsername());
      statement.executeUpdate();
    }
  }

  @Override public void delete(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM account WHERE username = ?");
      statement.setString(1, account.getUsername());
      statement.executeUpdate();
    }
  }

  @Override public Account readByUsernameID(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM account WHERE username = ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String username = resultSet.getString("username");
        double balance = resultSet.getDouble("balance");
        Account account = new Account(username, balance);
        return account;
      }
      else
      {
        return null;
      }
    }
  }
}
