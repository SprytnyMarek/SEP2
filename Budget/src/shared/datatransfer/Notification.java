package shared.datatransfer;

import java.io.Serializable;

public class Notification implements Serializable
{
  private String usernameAsking;
  private String usernameOwning;
  private double amount;

  public Notification(String usernameAsking, String usernameOwning,
      double amount)
  {
    this.usernameAsking = usernameAsking;
    this.usernameOwning = usernameOwning;
    this.amount = amount;
  }

  public String getUsernameAsking()
  {
    return usernameAsking;
  }

  public void setUsernameAsking(String usernameAsking)
  {
    this.usernameAsking = usernameAsking;
  }

  public String getUsernameOwning()
  {
    return usernameOwning;
  }

  public void setUsernameOwning(String usernameOwning)
  {
    this.usernameOwning = usernameOwning;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  public String toString(){
    return usernameOwning + " owes to " + usernameAsking + " " + amount + "euro";
  }
}
