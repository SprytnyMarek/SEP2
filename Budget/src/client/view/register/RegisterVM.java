package client.view.register;

import client.model.Model;

import client.model.RegisterModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterVM
{
  private StringProperty username, email, password, repeatPassword, registerLabel;
  private RegisterModel model;

  public RegisterVM(RegisterModel model){
    this.model = model;
    username = new SimpleStringProperty();
    email = new SimpleStringProperty();
    password = new SimpleStringProperty();
    repeatPassword = new SimpleStringProperty();
    registerLabel = new SimpleStringProperty();
  }

  public StringProperty usernameProperty(){return username;}
  public StringProperty emailProperty(){return email;}
  public StringProperty passwordProperty(){return password;}
  public StringProperty repeatPasswordProperty(){return repeatPassword;}
  public StringProperty registerLabel(){return registerLabel;}

  //sets label "OK" or Invalid email, username, password
  public String registerUser(){
    String registerResult =  model.registerUser(username.get(), email.get(), password.get(), repeatPassword.get());
    Platform.runLater(()-> registerLabel.set(registerResult));
    return registerResult;
  }

  public void clear(){
    username.set("");
    email.set("");
    password.set("");
    repeatPassword.set("");
    registerLabel.set("");
  }
}
