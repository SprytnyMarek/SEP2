package client.view.main.AddSpendings;

import client.model.Model;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.datatransfer.SpendingsInfo;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AddSpendingsVM implements PropertyChangeListener
{
  private Model model;
  private StringProperty amount;
  private ObservableList<String> categories;
  private ObservableList<SpendingsInfo> spendingsInfo;


  public AddSpendingsVM(Model model)
  {
    this.model = model;
    amount = new SimpleStringProperty();
    this.model.addPropertyChangeListener(this);
    categories = FXCollections.observableArrayList(model.getStringCategories());
    spendingsInfo = FXCollections.observableArrayList(model.getSpendingsInfos());
  }

  public ObservableList<SpendingsInfo> getSpendingsInfos(){
    return spendingsInfo;
  }


  public StringProperty getAmountProperty()
  {
    return amount;
  }

  public ObservableList<String> getStringCategories()
  {
    return categories;
  }



  public void spendingsTransfer(String categoryToSend)
  {
    boolean isNumeric;
    double money = 0;
    if(amount.get() == null || amount.get().equals("")){
      isNumeric = false;
    }
    else {
      try {
        money = Double.parseDouble(amount.get());
        isNumeric = true;
      } catch (NumberFormatException nfe) {
        isNumeric = false;
      }
    }
    if(isNumeric && money>0)
    {
      model.spendingsTransfer(categoryToSend, money);
    }
  }


  public void clear(){
    amount.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if (propertyChangeEvent.getPropertyName().equals("PopulateCategoryList"))
    {
      ArrayList arrayList = (ArrayList<SpendingsInfo>) propertyChangeEvent.getNewValue();
      Platform.runLater(()->spendingsInfo.add((SpendingsInfo) arrayList.get(arrayList.size()-1))
    );}
  }


}
