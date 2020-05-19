package client.view.main.sendMoney;

import client.core.ViewHandler;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SendMoneyController
{
  @FXML
  public JFXComboBox chooseUserList;
  @FXML
  private TextField moneyTransferDescription;
  @FXML
  private TextField moneyTransferAmount;
  @FXML
  private Button moneyTransferButton;
  private ObservableList observableList;


  private ViewHandler vh;
  private SendMoneyVM vm;


  public void init(SendMoneyVM sendMoneyVM, ViewHandler viewHandler)
  {
    this.vm = sendMoneyVM;
    this.vh = viewHandler;
    moneyTransferDescription.textProperty().bindBidirectional(vm.getDescriptionProperty());
    moneyTransferAmount.textProperty().bindBidirectional(vm.getAmountProperty());
    observableList = FXCollections.observableArrayList(vm.getStringUsernames());
    chooseUserList.setItems(observableList);
    chooseUserList.getSelectionModel().selectFirst();
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

  public void moneyTransferButton(ActionEvent actionEvent)
  {
    String userToSend = chooseUserList.getSelectionModel().getSelectedItem().toString();
    vm.moneyTransfer(userToSend);
    vm.clear();
  }
}
