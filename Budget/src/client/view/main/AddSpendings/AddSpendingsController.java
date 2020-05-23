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

public class AddSpendingsController implements PropertyChangeListener
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
  private ObservableList observableList;


  public void init(AddSpendingsVM addSpendingsVM, ViewHandler viewHandler)
  {
    this.vm = addSpendingsVM;
    this.vh = viewHandler;
    spendingsAmount.textProperty().bindBidirectional(vm.getAmountProperty());
    observableList = FXCollections.observableArrayList(vm.getStringCategories());
    spendingsCategory.setItems(observableList);
    spendingsCategory.getSelectionModel().selectFirst();
    this.vm.addPropertyChangeListener(this::propertyChange);
    new Thread(()->{
      System.out.println("Start thread");
      ArrayList<SpendingsInfo> spendingsInfos = vm.getSpendingsInfos();
      spendingsListView.getItems().clear();
      if(spendingsInfos.size()>0){
        for(int i=0; i<spendingsInfos.size();i++){
          spendingsListView.getItems().add(spendingsInfos.get(i).getCategory() + " : " + spendingsInfos.get(i).getAmount());
        }
      }
    }).start();
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

  public void spendingsTransferButton(ActionEvent actionEvent)
  {
    String categoryToSend = spendingsCategory.getSelectionModel().getSelectedItem().toString();
    vm.spendingsTransfer(categoryToSend);
    vm.clear();
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if(propertyChangeEvent.getPropertyName().equals("PopulateCategoryList")){
      ArrayList<SpendingsInfo> spendingsInfos = (ArrayList<SpendingsInfo>) propertyChangeEvent.getNewValue();
      Platform.runLater(()->{spendingsListView.getItems().clear();
        if(spendingsInfos.size()>0){
          for(int i=0; i<spendingsInfos.size();i++){
            spendingsListView.getItems().add(spendingsInfos.get(i).getCategory() + " : " + spendingsInfos.get(i).getAmount());
          }
        }});
    }
  }
}
