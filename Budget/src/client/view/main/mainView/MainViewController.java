package client.view.main.mainView;

import client.core.ViewHandler;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class MainViewController
{
  @FXML
  private JFXTextField budgetField;
  @FXML
  private Button setButton;
  @FXML
  private Label amountLabel;



  private ViewHandler vh;
  private MainVM vm;


  public void init(MainVM mainVm, ViewHandler viewHandler)
  {
    this.vm = mainVm;
    this.vh = viewHandler;
    amountLabel.textProperty().bindBidirectional(vm.amountProperty());
    budgetField.textProperty().bindBidirectional(vm.budgetProperty());
    new Thread(()->{
        vm.showAmount();
        try
        {
          Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
    }).start();
  }

  public void onSetButton(ActionEvent actionEvent)
  {
    vm.addBudget();
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
