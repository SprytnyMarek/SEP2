package client.core;


import client.view.login.LoginController;
import client.view.main.AddSpendings.AddSpendingsController;
import client.view.main.mainView.MainViewController;
import client.view.main.sendMoney.SendMoneyController;
import client.view.register.RegisterController;
import javafx.application.Platform;
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
    stage.setOnCloseRequest(windowEvent -> {
      vmf.unregisterUser();
      Platform.exit();
      System.exit(0);
    });
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
    loader.setLocation(getClass().getResource("../view/main/mainView/mainView.fxml"));
    try
    {
      Parent root = loader.load();
      MainViewController ctrl = loader.getController();
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
    loader.setLocation(getClass().getResource("../view/main/sendMoney/sendMoney.fxml"));
    try
    {
      Parent root = loader.load();
      SendMoneyController ctrl = loader.getController();
      ctrl.init(vmf.getSendMoneyVM(), this);
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
    loader.setLocation(getClass().getResource("../view/main/AddSpendings/addSpendings.fxml"));
    try
    {
      Parent root = loader.load();
      AddSpendingsController ctrl = loader.getController();
      ctrl.init(vmf.getAddSpendingsVM(), this);
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
