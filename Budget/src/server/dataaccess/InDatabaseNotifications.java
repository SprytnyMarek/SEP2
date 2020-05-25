package server.dataaccess;

import dao.accountDAO.AccountDAO;
import dao.accountDAO.AccountDAOImpl;
import dao.notificationsDAO.NotificationsDAO;
import dao.notificationsDAO.NotificationsDAOImpl;
import dao.spendingsDAO.SpendingsDAO;
import dao.spendingsDAO.SpendingsDAOImpl;
import shared.datatransfer.Account;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public class InDatabaseNotifications implements Notifications
{
  @Override public void addNotification(String usernameAsking,
      String usernameOwing, double amount)
  {
    try
    {
      NotificationsDAO dao = NotificationsDAOImpl.getInstance();
      dao.createNotifications(usernameAsking, usernameOwing, amount);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<Notification> getNotificationList(String username)
  {
    try
    {
      NotificationsDAO dao = NotificationsDAOImpl.getInstance();
      ArrayList<Notification> notificationList = dao.readByUsernameID(username);
      return notificationList;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
