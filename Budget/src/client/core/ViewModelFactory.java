package client.core;

import client.view.login.LoginVM;
import client.view.main.AddSpendings.AddSpendingsVM;
import client.view.main.adminView.AdminVM;
import client.view.main.mainView.MainVM;
import client.view.main.notification.NotificationVM;
import client.view.main.sendMoney.SendMoneyVM;
import client.view.register.RegisterVM;

public class ViewModelFactory
{
  private ModelFactory mf;
  private LoginVM loginVm;
  private RegisterVM registerVm;
  private MainVM mainVm;
  private SendMoneyVM sendMoneyVM;
  private AddSpendingsVM addSpendingsVM;
  private NotificationVM notificationVM;
  private AdminVM adminVM;

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

  public MainVM getMainVm(){
    if(mainVm == null){
      mainVm = new MainVM(mf.getModel());
    }
    return mainVm;
  }

  public AdminVM getAdminVm(){
    if(adminVM == null){
      adminVM = new AdminVM(mf.getModel());
    }
    return adminVM;
  }

  public SendMoneyVM getSendMoneyVM(){
    if(sendMoneyVM == null){
      sendMoneyVM = new SendMoneyVM(mf.getModel());
    }
    return sendMoneyVM;
  }

  public AddSpendingsVM getAddSpendingsVM(){
    if(addSpendingsVM == null){
      addSpendingsVM = new AddSpendingsVM(mf.getModel());
    }
    return addSpendingsVM;
  }

  public NotificationVM getNotificationVM(){
    if(notificationVM == null){
      notificationVM = new NotificationVM(mf.getModel());
    }
    return notificationVM;
  }

  public void unregisterUser()
  {
    mf.unregisterUser();
  }
}
