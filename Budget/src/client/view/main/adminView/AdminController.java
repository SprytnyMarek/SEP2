package client.view.main.adminView;

import client.core.ViewHandler;
import client.view.main.notification.NotificationVM;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AdminController
{
  public JFXListView notificationListView;
  @FXML
  private ComboBox adminUser;

  private ViewHandler vh;
  private AdminVM vm;



  public void init(AdminVM adminVM, ViewHandler viewHandler)
  {
    this.vm = adminVM;
    this.vh = viewHandler;
    adminUser.setItems(vm.getStringUsernames());
    adminUser.getSelectionModel().selectFirst();
  }


  public void onSearch(ActionEvent actionEvent)
  {
    String userToGet = adminUser.getSelectionModel().getSelectedItem().toString();
    vm.getSpendingsInfo(userToGet);
    notificationListView.setItems(vm.getSpendingsInfos());
  }

  public void onGoHomeButton(MouseEvent actionEvent)
  {

  }
  public void onGoSendMoneyButton(MouseEvent actionEvent)
  {

  }
  public void onGoChartsButton(MouseEvent actionEvent)
  {

  }
  public void onGoSpendingsButton(MouseEvent actionEvent)
  {

  }
  public void onGoNotificationsButton(MouseEvent actionEvent)
  {

  }
}
