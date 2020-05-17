package client.view.main;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainVM implements PropertyChangeListener
{
  private Model model;
  private StringProperty amountLabel;
  private StringProperty budgetField;

  public MainVM(Model model)
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
    double budget = Double.parseDouble(budgetField.get());
    if(budget>0){
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
  }
}
