package client.view.register;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.login.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController
{
  @FXML
  private TextField registerUsername;
  @FXML
  private TextField emailField;
  @FXML
  private TextField registerPassword;
  @FXML
  private TextField registerRepeatPassword;
  @FXML
  private Label registerLabel;

  private RegisterVM vm;
  private ViewHandler vh;

  public void onBackToLogin()
  {
    goToLogin();
  }

  public void init(RegisterVM registerVM, ViewHandler viewHandler)
  {
    this.vm = registerVM;
    this.vh = viewHandler;
    registerUsername.textProperty().bindBidirectional(vm.usernameProperty());
    emailField.textProperty().bindBidirectional(vm.emailProperty());
    registerPassword.textProperty().bindBidirectional(vm.passwordProperty());
    registerRepeatPassword.textProperty().bindBidirectional(vm.repeatPasswordProperty());
    registerLabel.textProperty().bindBidirectional(vm.registerLabel());
  }

  public void registerButton(){
    String registerResult = vm.registerUser();
    if(registerResult.equals("OK")){
      vm.clear();
    }
  }

  public void goToLogin(){
    vh.openLoginView();
  }
}
