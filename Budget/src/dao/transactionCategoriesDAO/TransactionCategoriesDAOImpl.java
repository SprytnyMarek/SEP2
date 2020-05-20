package dao.transactionCategoriesDAO;

import dao.transactionDAO.TransactionDAOImpl;
import shared.datatransfer.Password;
import shared.datatransfer.Transaction;
import shared.datatransfer.TransactionCategories;

import java.sql.*;
import java.util.ArrayList;

public class TransactionCategoriesDAOImpl implements TransactionCategoriesDAO
{
  private static TransactionCategoriesDAOImpl instance;

  private TransactionCategoriesDAOImpl() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  //makes instance so every client access the same file
  public static synchronized TransactionCategoriesDAOImpl getInstance() throws SQLException {
    if (instance == null) {
      instance = new TransactionCategoriesDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"SEP2\"",
        "postgres", Password.getPassword());
  }

  @Override public TransactionCategories create(int id, String title) throws SQLException
  {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO transaction (int id, String title) VALUES(?, ?) ;");
      statement.setInt(1, id);
      statement.setString(2, title);
      statement.executeUpdate();
      return new TransactionCategories(id, title);
    }
  }

  @Override public void update(TransactionCategories transactionCategories)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE transaction SET id=?, title=?");
      statement.setInt(1, transactionCategories.getId());
      statement.setString(2, transactionCategories.getTitle());
      statement.executeUpdate();
    }
  }

  @Override public void delete(TransactionCategories transactionCategories)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM transaction WHERE id = ?");
      statement.setInt(1, transactionCategories.getId());
      statement.executeUpdate();
    }
  }

  @Override public TransactionCategories readByUsernameID(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM transaction WHERE id = ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        TransactionCategories transactionCategories = new TransactionCategories(id, title);
        return transactionCategories;
      }
      else
      {
        return null;
      }
    }
  }

  @Override public ArrayList getStringCategories() throws SQLException
  {
    ArrayList<String> categories = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT title FROM transactioncategories");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next())
      {
        String title = resultSet.getString("title");
        categories.add(title);
      }
      return categories;
    }
  }
}
