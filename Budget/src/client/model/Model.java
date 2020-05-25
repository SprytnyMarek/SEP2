package client.model;

import shared.datatransfer.SpendingsInfo;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model extends  LoginModel, RegisterModel, MainViewModel
{
 void unregisterUser();

}
