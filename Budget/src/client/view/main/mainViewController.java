package client.view.main;

import client.core.ViewHandler;
import client.view.login.LoginVM;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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



  public void init(MainVM mainVm, ViewHandler viewHandler)
  {
    this.vm = mainVm;
    this.vh = viewHandler;
  }

  public void onSetButton(ActionEvent actionEvent)
  {
  }
}
