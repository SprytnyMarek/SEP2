package client.view.main;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainVM
{
  private Model model;
  private StringProperty amountLabel;

  public MainVM(Model model)
  {
    this.model = model;
    amountLabel = new SimpleStringProperty();
  }

  public StringProperty amountProperty(){
    return amountLabel;
  }

  public void showAmount(String username){
    double amount = model.getBudget(username);
    String amountValue = Double.toString(amount);
    Platform.runLater(()->amountLabel.set(amountValue));
  }
}
