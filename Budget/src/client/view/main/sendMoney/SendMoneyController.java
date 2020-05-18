package client.view.main.sendMoney;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SendMoneyController
{
  @FXML
  private ComboBox chooseUserComboBox;
  @FXML
  private TextField moneyTransferDescription;
  @FXML
  private TextField moneyTransferAmount;
  @FXML
  private Button moneyTransferButton;


  private ViewHandler vh;
  private SendMoneyVM vm;


  public void init(SendMoneyVM sendMoneyVM, ViewHandler viewHandler)
  {
    this.vm = sendMoneyVM;
    this.vh = viewHandler;
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
}
