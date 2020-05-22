package client.view.main.AddSpendings;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.datatransfer.SpendingsInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AddSpendingsVM implements PropertyChangeListener
{
  private Model model;
  private StringProperty amount;
  private PropertyChangeSupport support;


  public AddSpendingsVM(Model model)
  {
    this.model = model;
    amount = new SimpleStringProperty();
    support = new PropertyChangeSupport(this);
    this.model.addPropertyChangeListener(this);
  }


  public StringProperty getAmountProperty()
  {
    return amount;
  }

  public ArrayList getStringCategories()
  {
    return model.getStringCategories();
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
    if(propertyChangeEvent.getPropertyName().equals("PopulateCategoryList")){
      support.firePropertyChange("PopulateCategoryList", null, propertyChangeEvent.getNewValue());
    }
  }
}
