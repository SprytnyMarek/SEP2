package client.core;

import javafx.application.Application;
import javafx.stage.Stage;

public class BudgetApp extends Application
{
  @Override public void start(Stage stage)
  {
    ClientFactory cf = new ClientFactory();
    ModelFactory mf = new ModelFactory(cf);
    ViewModelFactory vmf = new ViewModelFactory(mf);
    ViewHandler viewHandler = new ViewHandler(vmf);
    viewHandler.start();
  }
}
