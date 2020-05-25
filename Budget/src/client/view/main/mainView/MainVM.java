package client.view.main.mainView;

import client.model.MainViewModel;
import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.datatransfer.TransactionInformation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainVM implements PropertyChangeListener
{
  private MainViewModel model;
  private StringProperty amountLabel;
  private StringProperty budgetField;

  public MainVM(MainViewModel model)
  {
    this.model = model;
    amountLabel = new SimpleStringProperty();
    budgetField = new SimpleStringProperty();
    this.model.addPropertyChangeListener(this);
  }

  public StringProperty amountProperty(){
    return amountLabel;
  }
  public StringProperty budgetProperty(){return budgetField;}

  public void showAmount(){
    double amount = model.getBudget();
    String amountValue = Double.toString(amount);
    Platform.runLater(()->amountLabel.set(amountValue));
  }

  public void addBudget()
  {
    boolean isNumeric;
    double budget = 0;
    if (budgetField.get() == null) {
      isNumeric = false;
    }
    try {
      budget = Double.parseDouble(budgetField.get());
      isNumeric = true;
    } catch (NumberFormatException nfe) {
      isNumeric = false;
    }
    if(budget>0 && isNumeric){
      model.addToBudget(budget);
    }
    clear();
  }

  public void clear(){
    budgetField.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    if(propertyChangeEvent.getPropertyName().equals("AddBudget")){
      double budget = Double.parseDouble(amountLabel.get()) + (double)propertyChangeEvent.getNewValue();
      Platform.runLater(()->this.amountLabel.setValue(Double.toString(budget)));
    }
    if(propertyChangeEvent.getPropertyName().equals("TransferSent")){
      TransactionInformation transactionInformation =(TransactionInformation)propertyChangeEvent.getNewValue();
      double budget = Double.parseDouble(amountLabel.get()) - transactionInformation.getMoney();
      Platform.runLater(()->this.amountLabel.setValue(Double.toString(budget)));
    }
    if(propertyChangeEvent.getPropertyName().equals("TransferReceived")){
      TransactionInformation transactionInformation = (TransactionInformation) propertyChangeEvent.getNewValue();
      double budget = Double.parseDouble(amountLabel.get()) + transactionInformation.getMoney();
      Platform.runLater(()->this.amountLabel.setValue(Double.toString(budget)));
    }
  }
}
