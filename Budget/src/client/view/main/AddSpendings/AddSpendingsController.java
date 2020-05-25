package client.view.main.AddSpendings;

import client.core.ViewHandler;
import client.view.main.sendMoney.SendMoneyVM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import shared.datatransfer.SpendingsInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AddSpendingsController
{
  @FXML
  private ComboBox spendingsCategory;
  @FXML
  private TextField spendingsAmount;
  @FXML
  private ListView spendingsListView;
  @FXML
  private JFXButton spendingsAddButton;

  private ViewHandler vh;
  private AddSpendingsVM vm;


  public void init(AddSpendingsVM addSpendingsVM, ViewHandler viewHandler)
  {
    this.vm = addSpendingsVM;
    this.vh = viewHandler;
    spendingsAmount.textProperty().bindBidirectional(vm.getAmountProperty());
    spendingsCategory.setItems(vm.getStringCategories());
    spendingsCategory.getSelectionModel().selectFirst();
    spendingsListView.setItems(vm.getSpendingsInfos());
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

  public void spendingsTransferButton(ActionEvent actionEvent)
  {
    String categoryToSend = spendingsCategory.getSelectionModel().getSelectedItem().toString();
    vm.spendingsTransfer(categoryToSend);
    vm.clear();
  }
}
