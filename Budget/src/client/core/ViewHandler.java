package client.core;

import client.view.ViewController;
import client.view.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
  }

  public void start()
  {
    stage = new Stage();
    openLoginView();
  }

  public void openLoginView()
  {
    if(scene == null){
      try{
        Parent root = loadFXML("../view/login/Login.fxml");
        scene = new Scene(root);
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
    stage.setTitle("Login");
    stage.setScene(scene);
    stage.show();
  }

  public void openRegisterView()
  {
    if(scene == null){
      try{
        Parent root = loadFXML("../view/register/Register.fxml");
        scene = new Scene(root);
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
    stage.setTitle("Register");
    stage.setScene(scene);
    stage.show();
  }

  private Parent loadFXML(String path) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();
    ViewController ctrl = loader.getController();
    ctrl.init(this, vmf);
    return root;
  }
}
