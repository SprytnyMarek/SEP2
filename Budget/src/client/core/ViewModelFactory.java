package client.core;

import client.view.login.LoginVM;

public class ViewModelFactory
{
  private ModelFactory mf;
  private LoginVM loginVm;

  public ViewModelFactory(ModelFactory mf){
    this.mf = mf;
  }

  public LoginVM getLoginVm(){
    if(loginVm == null){
      loginVm = new LoginVM(mf.getModel());
    }
    return loginVm;
  }
}
