package dao.notificationsDAO;

import shared.datatransfer.Notification;
import shared.datatransfer.Password;

import java.sql.*;
import java.util.ArrayList;

public class NotificationsDAOImpl implements NotificationsDAO
{
  private static NotificationsDAOImpl instance;

  private NotificationsDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized NotificationsDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new NotificationsDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"SEP2\"",
        "postgres", Password.getPassword());
  }

  @Override public void createNotifications(String usernameAsking,
      String usernameOwing, double amount) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO notifications(usernameasking, usernameowing, amountofmoney) VALUES(?, ?, ?) ;");
      statement.setString(1, usernameAsking);
      statement.setString(2, usernameOwing);
      statement.setDouble(3, amount);
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<Notification> readByUsernameID(
      String searchString) throws SQLException
  {
    System.out.println("2");
    ArrayList<Notification> arrayList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM notifications WHERE usernameasking = ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String usernameAsking = resultSet.getString("usernameasking");
        String usernameOwing = resultSet.getString("usernameowing");
        double money = resultSet.getDouble("amountofmoney");
        Notification notification = new Notification(usernameAsking, usernameOwing, money);
        arrayList.add(notification);
      }
      PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM notifications WHERE usernameowing = ? ");
      statement2.setString(1, searchString);
      ResultSet resultSet2 = statement2.executeQuery();
      while (resultSet2.next())
      {
        String usernameAsking = resultSet.getString("usernameasking");
        String usernameOwing = resultSet.getString("usernameowing");
        double money = resultSet.getDouble("amountofmoney");
        Notification notification = new Notification(usernameAsking, usernameOwing, money);
        arrayList.add(notification);
      }
      return arrayList;
    }
  }
}
