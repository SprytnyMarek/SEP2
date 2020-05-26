package shared.datatransfer;

public class Account
{
  private String username;
  private double balance;
  //timePeriod

  public Account(String username, double balance)
  {
    this.username = username;
    this.balance = balance;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public double getBalance()
  {
    return balance;
  }

  public void setBalance(double balance)
  {
    this.balance = balance;
  }

}
