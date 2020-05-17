package client.view.main;

import client.core.ViewHandler;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class mainViewController
{
  @FXML
  private Label budgetLabel;
  @FXML
  private Label newBudgetLabel;
  @FXML
  private TextField budgetField;
  @FXML
  private Button setButton;
  @FXML
  private Label amountLabel;
  @FXML
  private ProgressIndicator progressIndicator;

  private ViewHandler vh;
  private MainVM vm;
  private boolean running = false;


  public void init(MainVM mainVm, ViewHandler viewHandler)
  {
    running = true;
    this.vm = mainVm;
    this.vh = viewHandler;
    amountLabel.textProperty().bindBidirectional(vm.amountProperty());
    budgetField.textProperty().bindBidirectional(vm.budgetProperty());
    new Thread(()->{
      while (running){
        vm.showAmount(vh.getUsername());
        try
        {
          Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void onSetButton(ActionEvent actionEvent)
  {
    vm.addBudget(vh.getUsername());
  }

  public void onGoHomeButton(MouseEvent actionEvent)
  {
    vh.openMainView();
  }
  public void onGoSendMoneyButton(MouseEvent actionEvent)
  {
    running = false;
    vh.openSendMoneyView();
  }
  public void onGoChartsButton(MouseEvent actionEvent)
  {

  }
  public void onGoSpendingsButton(MouseEvent actionEvent)
  {
    running = false;
    vh.openAddSpendingsView();
  }
}
