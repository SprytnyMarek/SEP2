package client.view.main.AddSpendings;

import client.core.ViewHandler;
import client.view.main.sendMoney.SendMoneyVM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddSpendingsController
{
  @FXML
  private ComboBox spendingsCategory;
  @FXML
  private TextField spendingsAmount;
  @FXML
  private JFXListView spendingsListView;
  @FXML
  private JFXButton spendingsAddButton;

  private ViewHandler vh;
  private AddSpendingsVM vm;
  private ObservableList observableList;


  public void init(AddSpendingsVM addSpendingsVM, ViewHandler viewHandler)
  {
    this.vm = addSpendingsVM;
    this.vh = viewHandler;
    //spendingsAmount.textProperty().bindBidirectional();
    observableList = FXCollections.observableArrayList(vm.getStringCategories());
    spendingsCategory.setItems(observableList);
    spendingsCategory.getSelectionModel().selectFirst();
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
