package client.view.login;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController
{
  @FXML
  private TextField loginUsername;
  @FXML
  private TextField loginPassword;
  @FXML
  private Label loginLabel;
  @FXML
  private Button loginButton;
  @FXML
  private Button goToRegistration;

  private ViewHandler vh;
  private LoginVM vm;


  //goes to registration view
  @FXML
  public void onSignUpButton()
  {
    vh.openRegisterView();
  }


  public void init(LoginVM loginVm, ViewHandler viewHandler)
  {
    this.vm = loginVm;
    this.vh = viewHandler;
    loginUsername.textProperty().bindBidirectional(vm.usernameProperty());
    loginPassword.textProperty().bindBidirectional(vm.passwordProperty());
    loginLabel.textProperty().bindBidirectional(vm.loginLabelProperty());
  }

  //goes to main page if login info are valid
  @FXML
  public void onLoginButton(){
    String loginResult = vm.loginResult();
    if("OK".equals(loginResult)){
      vm.clear();
      vh.openMainView();

    }
    if ("Open Admin View".equals(loginResult))
    {
      vm.clear();
      vh.openAdminView();
    }
  }
}
