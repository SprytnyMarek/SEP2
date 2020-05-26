package client.view.main.notification;

import client.core.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

public class NotificationController
{
  @FXML
  private ComboBox notificationUser;
  @FXML
  private TextField notificationAmount;
  @FXML
  private ListView notificationListView;

  private ViewHandler vh;
  private NotificationVM vm;


  public void init(NotificationVM notificationVM, ViewHandler viewHandler)
  {
    this.vm = notificationVM;
    this.vh = viewHandler;
    notificationAmount.textProperty().bindBidirectional(vm.getNotificationAmountProperty());
    notificationUser.setItems(vm.getStringUsernames());
    notificationUser.getSelectionModel().selectFirst();
    notificationListView.setItems(vm.getNotificationList());
  }

  public void onGoHomeButton(MouseEvent actionEvent)
  {
    vh.openMainView();
  }
  public void onGoSendMoneyButton(MouseEvent actionEvent)
  {
    vh.openSendMoneyView();
  }
  public void onGoChartsButton(MouseEvent actionEvent)
  {

  }
  public void onGoSpendingsButton(MouseEvent actionEvent)
  {
    vh.openAddSpendingsView();
  }
  public void onGoNotificationsButton(MouseEvent actionEvent)
  {
    vh.openNotificationView();
  }

  public void onAddNotification(ActionEvent actionEvent)
  {
    String userToSend = notificationUser.getSelectionModel().getSelectedItem().toString();
    vm.addNotification(userToSend);
    vm.clear();
  }
}
