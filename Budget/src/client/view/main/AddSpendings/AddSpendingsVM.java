package client.view.main.AddSpendings;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class AddSpendingsVM
{
  private Model model;
  private StringProperty amount;

  public AddSpendingsVM(Model model)
  {
    this.model = model;
    amount = new SimpleStringProperty();;
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
}
