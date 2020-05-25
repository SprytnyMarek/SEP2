package server.dataaccess;

import shared.datatransfer.Notification;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Notifications
{
  void addNotification(String usernameAsking, String usernameOwing, double amount);
  ArrayList<Notification> getNotificationList(String username);
}
