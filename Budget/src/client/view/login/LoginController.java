package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements ViewController
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


  public void onSignUpButton()
  {
    goToRegistration();
  }


  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    vm = vmf.getLoginVm();
    loginUsername.textProperty().bindBidirectional(vm.usernameProperty());
    loginPassword.textProperty().bindBidirectional(vm.passwordProperty());
    loginLabel.textProperty().bindBidirectional(vm.loginLabelProperty());
  }

  @FXML
  public void onLoginButton(){
    String loginResult = vm.loginResult();
    if("OK".equals(loginResult)){
      vm.clear();
      //changeToMainView
    }
  }
  @FXML
  public void goToRegistration(){
    vh.openRegisterView();
  }
}