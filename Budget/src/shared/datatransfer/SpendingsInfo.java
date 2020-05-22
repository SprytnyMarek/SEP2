package shared.datatransfer;

import java.io.Serializable;

public class SpendingsInfo implements Serializable
{
  private String category;
  private double amount;
  private String username;

  public SpendingsInfo(String username, String category, double amount)
  {
    this.username = username;
    this.category = category;
    this.amount = amount;
  }

  public String getCategory()
  {
    return category;
  }

  public void setCategory(String category)
  {
    this.category = category;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}
