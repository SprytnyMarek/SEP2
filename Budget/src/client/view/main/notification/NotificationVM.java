package client.view.main.notification;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class NotificationVM
{
  private Model model;
  private StringProperty notificationAmount;
  private ObservableList<String> listOfUsernames;

  public NotificationVM(Model model)
  {
    this.model = model;
    notificationAmount = new SimpleStringProperty();
    listOfUsernames = FXCollections.observableArrayList(model.getStringUsernames());
  }


  public StringProperty getNotificationAmountProperty()
  {
    return notificationAmount;
  }

  public ObservableList<String> getStringUsernames()
  {
    return listOfUsernames;
  }


}
