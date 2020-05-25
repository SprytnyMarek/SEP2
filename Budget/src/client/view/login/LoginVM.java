package client.view.login;

import client.model.LoginModel;
import client.model.Model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginVM
{
  private StringProperty username, password, loginLabel;
  private LoginModel model;

  public LoginVM(LoginModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    loginLabel = new SimpleStringProperty();
  }

  public StringProperty usernameProperty(){return username;}
  public StringProperty passwordProperty(){return password;}
  public StringProperty loginLabelProperty(){return loginLabel;}

  //sets the label for login "OK", "Password incorrect", "User not found"
  public String loginResult(){
    String loginResult =  model.loginResult(username.get(), password.get());
    Platform.runLater(()-> loginLabel.set(loginResult));
    return loginResult;
  }

  public void clear(){
    username.set("");
    password.set("");
    loginLabel.set("");
  }
}
