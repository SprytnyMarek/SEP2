package client.view.main.notification;

import client.model.MainViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.datatransfer.Notification;
import shared.datatransfer.SpendingsInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class NotificationVM implements PropertyChangeListener
{
  private MainViewModel model;
  private StringProperty notificationAmount;
  private ObservableList<String> listOfUsernames;
  private ObservableList<Notification> notificationList;

  public NotificationVM(MainViewModel model)
  {
    this.model = model;
    notificationAmount = new SimpleStringProperty();
    listOfUsernames = FXCollections.observableArrayList(model.getStringUsernames());
    notificationList = FXCollections.observableArrayList(model.getNotificationList());
  }


  public StringProperty getNotificationAmountProperty()
  {
    return notificationAmount;
  }

  public ObservableList<Notification> getNotificationList()
  {
    return notificationList;
  }

  public ObservableList<String> getStringUsernames()
  {
    return listOfUsernames;
  }

  public void addNotification(String userToSend)
  {
    boolean isNumeric;
    double money = 0;
    if(notificationAmount.get() == null || notificationAmount.get().equals("")){
      isNumeric = false;
    }
    else {
      try {
        money = Double.parseDouble(notificationAmount.get());
        isNumeric = true;
      } catch (NumberFormatException nfe) {
        isNumeric = false;
      }
    }
    if(isNumeric && money>0)
    {
      model.addNotification(userToSend, money);
    }
  }



  public void clear()
  {
    notificationAmount.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if (propertyChangeEvent.getPropertyName().equals("AddInNotificationList"))
    {
      Notification notification = (Notification) propertyChangeEvent.getNewValue();
      Platform.runLater(()->notificationList.add(notification)
      );}
  }
}
