package dao.spendingsDAO;

import dao.accountDAO.AccountDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.Password;
import shared.datatransfer.SpendingsInfo;

import java.sql.*;

public class SpendingsDAOImpl implements SpendingsDAO
{

  private static SpendingsDAOImpl instance;

  public SpendingsDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  //makes instance so every client access the same file
  public static synchronized SpendingsDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new SpendingsDAOImpl();
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



  @Override public SpendingsInfo create(String username, String category, double money) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO transaction(username, categorycode, amountofmoney) VALUES(?, ?, ?) ;");
      statement.setString(1, username);
      statement.setString(2, category);
      statement.setDouble(3, money);
      statement.executeUpdate();
      return new SpendingsInfo(username, category, money);
    }
  }

  @Override public void update(SpendingsInfo spendingsInfo) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE transaction SET username=?, categorycode =?, amountofmoney=? WHERE username =?");
      statement.setString(1, spendingsInfo.getUsername());
      statement.setString(2, spendingsInfo.getCategory());
      statement.setDouble(3, spendingsInfo.getAmount());
      statement.executeUpdate();
    }
  }

  @Override public void delete(SpendingsInfo spendingsInfo) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM transaction WHERE username = ?");
      statement.setString(1, spendingsInfo.getUsername());
      statement.executeUpdate();
    }
  }

  @Override public SpendingsInfo readByUsernameID(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM transaction WHERE username = ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String username = resultSet.getString("username");
        String category = resultSet.getString("categorycode");
        double money = resultSet.getDouble("amountofmoney");
        SpendingsInfo spendingsInfo = new SpendingsInfo(username, category, money);
        return spendingsInfo;
      }
      else
      {
        return null;
      }
    }
  }
}
