package client.core;


import client.view.login.LoginController;
import client.view.main.mainViewController;
import client.view.register.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory vmf;
  private Stage stage;
  private Scene scene;

  public ViewHandler(ViewModelFactory vmf)
  {
    this.vmf = vmf;
    stage = new Stage();
  }

  public void start()
  {
    openLoginView();
    stage.show();
  }

  public void openLoginView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/login/Login.fxml"));
    try
    {
      Parent root = loader.load();
      LoginController ctrl = loader.getController();
      ctrl.init(vmf.getLoginVm(), this);
      stage.setTitle("Login");
      Scene loginScene = new Scene(root);
      stage.setScene(loginScene);
      stage.getIcons().add(new Image("file:../view/login/corona.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openRegisterView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/register/Register.fxml"));
    try
    {
      Parent root = loader.load();
      RegisterController ctrl = loader.getController();
      ctrl.init(vmf.getRegisterVm(), this);
      stage.setTitle("Register");
      Scene registerScene = new Scene(root);
      stage.setScene(registerScene);
      stage.getIcons().add(new Image("file:../view/login/corona.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  public void openMainView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/main/mainView.fxml"));
    try
    {
      Parent root = loader.load();
      mainViewController ctrl = loader.getController();
      ctrl.init(vmf.getMainVm(), this);
      stage.setTitle("Corona");
      Scene mainScene = new Scene(root);
      stage.setScene(mainScene);
      stage.getIcons().add(new Image("file:../view/login/corona.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openSendMoneyView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/main/sendMoney.fxml"));
    try
    {
      Parent root = loader.load();
      mainViewController ctrl = loader.getController();
      ctrl.init(vmf.getMainVm(), this);
      stage.setTitle("Corona");
      Scene mainScene = new Scene(root);
      stage.setScene(mainScene);
      stage.getIcons().add(new Image("file:../view/login/corona.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openAddSpendingsView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/main/addSpendings.fxml"));
    try
    {
      Parent root = loader.load();
      mainViewController ctrl = loader.getController();
      ctrl.init(vmf.getMainVm(), this);
      stage.setTitle("Corona");
      Scene mainScene = new Scene(root);
      stage.setScene(mainScene);
      stage.getIcons().add(new Image("file:../view/login/corona.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
