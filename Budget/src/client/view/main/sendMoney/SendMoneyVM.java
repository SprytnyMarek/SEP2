package client.view.main.sendMoney;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SendMoneyVM
{
  private Model model;
  private StringProperty description;
  private StringProperty amount;
  private ObservableList<String> listOfUsernames;

  public SendMoneyVM(Model model)
  {
    this.model = model;
    description = new SimpleStringProperty();
    amount = new SimpleStringProperty();
    listOfUsernames = FXCollections.observableArrayList(model.getStringCategories());
  }

  public StringProperty getDescriptionProperty(){
    return description;
  }

  public StringProperty getAmountProperty(){
    return amount;
  }

  public ObservableList<String> getStringUsernames()
  {
    return listOfUsernames;
  }

  public void moneyTransfer(String userToSend)
  {
    boolean isNumeric;
    double money = 0;
    String text = "";
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
    if(isNumeric && money>0){
      if(description==null ||description.equals("")){
        text = "No description";
      }
      else {
        text = description.get();
      }
      model.moneyTransfer(userToSend, money, text);
    }
  }



  public void clear(){
    description.set("");
    amount.set("");
  }
}
