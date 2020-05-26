package dao.transactionCategoriesDAO;

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
