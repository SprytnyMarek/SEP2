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

  /**
   * a user that was just registered does not have any notifications
   */
  @Test
  public void getEmptyList(){
    ArrayList<Notification> notificationList = notifications.getNotificationList("pawel");
    assertEquals(0, notificationList.size());
  }

  /**
   * a notification is added in the database
   */
  @Test
  public void addNotification(){
    boolean asserttrue = false;
    notifications.addNotification("pawel", "troels", 20);
    ArrayList<Notification> notificationList = notifications.getNotificationList("pawel");
    for(Notification n: notificationList){
      if(n.getUsernameAsking().equals("pawel") && n.getUsernameOwning().equals("troels") && n.getAmount() == 10){
        asserttrue = true;
      }
    }
    assertTrue(asserttrue);
  }

  /**
   * a notification is added to both users
   */
  @Test
  public void assertBothUsersGotTheSameNotification(){
    boolean asserttrue = false;
    boolean asserttrue2 = false;
    boolean asserttrueboth = false;
    notifications.addNotification("pawel", "troels", 10);
    ArrayList<Notification> notificationList = notifications.getNotificationList("pawel");
    for(Notification n: notificationList){
      if(n.getUsernameAsking().equals("pawel") && n.getUsernameOwning().equals("troels") && n.getAmount() == 10){
        asserttrue = true;
      }
    }
    ArrayList<Notification> notificationList2 = notifications.getNotificationList("troels");
    for(Notification n: notificationList2){
      if(n.getUsernameAsking().equals("pawel") && n.getUsernameOwning().equals("troels") && n.getAmount() == 10){
        asserttrue2 = true;
      }
    }
    if(asserttrue && asserttrue2){
      asserttrueboth = true;
    }
    assertTrue(asserttrueboth);
  }

  /**
   * the notification will be added only to the two users involved
   */
  @Test
  public void assertAnotherUserIsNotGettingTheNotification(){
    boolean asserttrue = true;
    notifications.addNotification("pawel", "troels", 10);
    ArrayList<Notification> notificationList = notifications.getNotificationList("Table");
    for(Notification n: notificationList){
      if(n.getUsernameAsking().equals("pawel") && n.getUsernameOwning().equals("troels") && n.getAmount() == 10){
        asserttrue = false;
      }
    }
    assertTrue(asserttrue);
  }


}