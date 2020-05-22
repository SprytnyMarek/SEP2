package shared.datatransfer;

import java.io.Serializable;

public class TransactionCategories implements Serializable
{
  private int id;
  private String title;

  public TransactionCategories(int id, String title)
  {
    this.id = id;
    this.title = title;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
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
