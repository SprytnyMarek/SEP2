package client.view.main.AddSpendings;

import client.model.Model;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class AddSpendingsVM
{
  private Model model;
  private StringProperty amount;

  public AddSpendingsVM(Model model, StringProperty amount)
  {
    this.model = model;
    this.amount = amount;
  }

  public AddSpendingsVM(Model model)
  {
    this.model = model;
  }

  public StringProperty getAmountProperty()
  {
    return amount;
  }

  public ArrayList getStringCategories()
  {
    return model.getStringCategories();
  }



}
