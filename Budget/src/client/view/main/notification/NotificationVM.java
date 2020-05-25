package client.view.main.notification;

import client.model.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotificationVM
{
  private MainViewModel model;
  private StringProperty notificationAmount;
  private ObservableList<String> listOfUsernames;

  public NotificationVM(MainViewModel model)
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
