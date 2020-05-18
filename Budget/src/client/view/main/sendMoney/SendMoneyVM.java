package client.view.main.sendMoney;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class SendMoneyVM
{
  private Model model;
  private StringProperty description;
  private StringProperty amount;

  public SendMoneyVM(Model model)
  {
    this.model = model;
    description = new SimpleStringProperty();
    amount = new SimpleStringProperty();
  }

  public StringProperty getDescriptionProperty(){
    return description;
  }

  public StringProperty getAmountProperty(){
    return amount;
  }

  public ArrayList getStringUsernames()
  {
    return model.getStringUsernames();
  }
}
