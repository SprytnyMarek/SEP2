package client.view.main;

import client.core.ViewHandler;
import client.view.login.LoginVM;

public class mainViewController
{
  private ViewHandler vh;
  private MainVM vm;


  public void init(MainVM mainVm, ViewHandler viewHandler)
  {
    this.vm = mainVm;
    this.vh = viewHandler;
  }
}
