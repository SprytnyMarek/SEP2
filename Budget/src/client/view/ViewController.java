package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.login.LoginVM;

public interface ViewController
{
  void init(LoginVM loginVm, ViewHandler viewHandler);

}
