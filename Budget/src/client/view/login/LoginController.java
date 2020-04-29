package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;

public class LoginController implements ViewController
{
  private ViewHandler vh;
  private LoginVM vm;


  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    vm = vmf.getLoginVm();
  }
}
