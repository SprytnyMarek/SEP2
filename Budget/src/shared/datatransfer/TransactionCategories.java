package shared.datatransfer;

import java.io.Serializable;

public class TransactionCategories implements Serializable
{
  private String title;

  public TransactionCategories(String title)
  {
    this.title = title;
  }



  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }
}
