package client.view.main.adminView;

import client.core.ViewHandler;
import client.view.main.notification.NotificationVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AdminController
{
  @FXML
  private ComboBox adminUser;

  private ViewHandler vh;
  private AdminVM vm;
  private ObservableList observableList;


  public void init(AdminVM adminVM, ViewHandler viewHandler)
  {
    this.vm = adminVM;
    this.vh = viewHandler;
    observableList = FXCollections.observableArrayList(vm.getStringUsernames());
    adminUser.setItems(observableList);
    adminUser.getSelectionModel().selectFirst();
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
}
