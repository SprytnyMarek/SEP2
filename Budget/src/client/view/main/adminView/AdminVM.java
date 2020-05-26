package client.view.main.adminView;

import client.model.AdminModel;
import client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

public class AdminVM
{
  private AdminModel model;
  private ObservableList observableList;
  private ObservableList spendingInfoList;

  public AdminVM(AdminModel model)
  {
    this.model = model;
    observableList = FXCollections.observableArrayList(model.getAllUsernamesForAdmin());

  }

  public ObservableList<SpendingsInfo> getSpendingsInfos(){
    return spendingInfoList;
  }

  public ObservableList getStringUsernames()
  {
    return observableList;
  }

  public void getSpendingsInfo(String userToGet)
  {
    ArrayList<SpendingsInfo> arrayList = model.getInfoForAdmin(userToGet);
    spendingInfoList = FXCollections.observableArrayList(arrayList);
  }
}
