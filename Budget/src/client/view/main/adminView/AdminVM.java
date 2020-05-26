package client.view.main.adminView;

import client.model.Model;

import java.util.ArrayList;

public class AdminVM
{
  private Model model;

  public AdminVM(Model model)
  {
    this.model = model;
  }

  public ArrayList getStringUsernames()
  {
    return model.getStringUsernames();
  }
}
