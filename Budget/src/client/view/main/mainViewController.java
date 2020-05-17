package client.view.main;

import client.core.ViewHandler;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class mainViewController
{
  //mainView.fxml
  @FXML
  private JFXTextField budgetField;
  @FXML
  private Button setButton;
  @FXML
  private Label amountLabel;

  //sendMoney.fxml
  @FXML
  private ComboBox chooseUserComboBox;
  @FXML
  private TextField moneyTransferDescription;
  @FXML
  private TextField moneyTransferAmount;
  @FXML
  private Button moneyTransferButton;

  //addSpendings.fxml
  @FXML
  private ComboBox spendingsCategory;
  @FXML
  private TextField spendingsAmount;
  @FXML
  private JFXListView spendingsListView;
  @FXML
  private JFXButton spendingsAddButton;


  private ViewHandler vh;
  private MainVM vm;



  public void init(MainVM mainVm, ViewHandler viewHandler)
  {
    this.vm = mainVm;
    this.vh = viewHandler;
    amountLabel.textProperty().bindBidirectional(vm.amountProperty());
    new Thread(()->{
      while (true){
        vm.showAmount(vh.getUsername());
        try
        {
          Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        System.out.println("Sleeping");
      }
    }).start();
  }

  public void onSetButton(ActionEvent actionEvent)
  {
  }

  public void onMoneyTransferButton(ActionEvent actionEvent)
  {
  }

  public void onSpendingsAddButton(ActionEvent actionEvent)
  {
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
