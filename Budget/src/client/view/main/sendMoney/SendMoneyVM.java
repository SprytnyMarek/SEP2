package client.view.main.sendMoney;

import client.model.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SendMoneyVM
{
  private MainViewModel model;
  private StringProperty description;
  private StringProperty amount;
  private ObservableList<String> listOfUsernames;

  public SendMoneyVM(MainViewModel model)
  {
    this.model = model;
    description = new SimpleStringProperty();
    amount = new SimpleStringProperty();
    listOfUsernames = FXCollections.observableArrayList(model.getStringUsernames());
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

  /**
   * method that checks if the amount given by the user is numeric and bigger than 0 and
   * if the description is empty it adds the description "No description"
   * @param userToSend the user that the money will be sent to
   */
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
