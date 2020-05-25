package dao.notificationsDAO;

import shared.datatransfer.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationsDAO
{
  void createNotifications(String usernameAsking, String usernameOwing, double amount) throws SQLException;
  ArrayList<Notification> readByUsernameID(String searchString) throws  SQLException;
}
