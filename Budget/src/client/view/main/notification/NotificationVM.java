package client.view.main.notification;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class NotificationVM
{
  private Model model;
  private StringProperty notificationAmount;

  public NotificationVM(Model model)
  {
    this.model = model;
    notificationAmount = new SimpleStringProperty();
  }


  public StringProperty getNotificationAmountProperty()
  {
    return notificationAmount;
  }

  public ArrayList getStringUsernames()
  {
    return model.getStringUsernames();
  }


}
