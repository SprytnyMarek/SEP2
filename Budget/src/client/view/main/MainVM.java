package client.view.main;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainVM
{
  private Model model;
  private StringProperty amountLabel;
  private StringProperty budgetField;

  public MainVM(Model model)
  {
    this.model = model;
    amountLabel = new SimpleStringProperty();
    budgetField = new SimpleStringProperty();
  }

  public StringProperty amountProperty(){
    return amountLabel;
  }
  public StringProperty budgetProperty(){return budgetField;}

  public void showAmount(String username){
    double amount = model.getBudget(username);
    String amountValue = Double.toString(amount);
    Platform.runLater(()->amountLabel.set(amountValue));
  }

  public void addBudget(String username)
  {
    double budget = Double.parseDouble(budgetField.get());
    String result  = model.addToBudget(username,budget);
    if(result.equals("OK")){
      clear();
    }
  }

  public void clear(){
    budgetField.set("");
  }
}
