package server.dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NotificationsTest
{
  private Notifications notifications;

  @BeforeEach
  public void setup(){
    notifications = new InDatabaseNotifications();
  }

  @Test
  public void getEmptyList(){
    ArrayList<Notification> notificationList = notifications.getNotificationList("pawel");
    assertEquals(0, notificationList.size());
  }

}