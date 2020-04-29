package client.core;

import client.view.login.LoginVM;
import client.view.register.RegisterVM;

public class ViewModelFactory
{
  private ModelFactory mf;
  private LoginVM loginVm;
  private RegisterVM registerVm;

  public ViewModelFactory(ModelFactory mf){
    this.mf = mf;
  }

  public LoginVM getLoginVm(){
    if(loginVm == null){
      loginVm = new LoginVM(mf.getModel());
    }
    return loginVm;
  }

  public RegisterVM getRegisterVm(){
    if(registerVm == null){
      registerVm = new RegisterVM(mf.getModel());
    }
    return registerVm;
  }
}
