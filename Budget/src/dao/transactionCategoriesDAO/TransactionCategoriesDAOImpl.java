package dao.transactionCategoriesDAO;

import dao.transactionDAO.TransactionDAOImpl;
import shared.datatransfer.Password;
import shared.datatransfer.Transaction;
import shared.datatransfer.TransactionCategories;

import java.sql.*;

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

  @Override public TransactionCategories create(String category_code, String category_description, String parent_category) throws SQLException
  {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO transaction (String category_code, String category_description, String parent_category) VALUES(?, ?, ?, ?) ;");
      statement.setString(1, category_code);
      statement.setString(2, category_description);
      statement.setString(3, parent_category);
      statement.executeUpdate();
      return new TransactionCategories(category_code, category_description, parent_category);
    }
  }

  @Override public void update(TransactionCategories transactionCategories)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE transaction SET category_code=?, category_description=?, parent_category =?");
      statement.setString(1, transactionCategories.getCategory_code());
      statement.setString(2, transactionCategories.getCategory_description());
      statement.setString(3, transactionCategories.getParent_category());
      statement.executeUpdate();
    }
  }

  @Override public void delete(TransactionCategories transactionCategories)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM transaction WHERE category_code = ?");
      statement.setString(1, transactionCategories.getCategory_code());
      statement.executeUpdate();
    }
  }

  @Override public TransactionCategories readByUsernameID(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM transaction WHERE amountOfMoney = ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String category_code = resultSet.getString("category_code");
        String category_description = resultSet.getString("category_description");
        String parent_category = resultSet.getString("parent_category");
        TransactionCategories transactionCategories = new TransactionCategories(category_code, category_description, parent_category);
        return transactionCategories;
      }
      else
      {
        return null;
      }
    }
  }
}
