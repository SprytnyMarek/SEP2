package shared.datatransfer;

public class Account
{
  private String username;
  private double balance;
  private double fixedPayments;
  private double fixedIncome;
  private double totalPayments;
  private double totalIncome;
  //timePeriod

  public Account(String username, double balance, double fixedPayments,
      double fixedIncome, double totalPayments, double totalIncome)
  {
    this.username = username;
    this.balance = balance;
    this.fixedPayments = fixedPayments;
    this.fixedIncome = fixedIncome;
    this.totalPayments = totalPayments;
    this.totalIncome = totalIncome;
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

  public double getFixedPayments()
  {
    return fixedPayments;
  }

  public void setFixedPayments(double fixedPayments)
  {
    this.fixedPayments = fixedPayments;
  }

  public double getFixedIncome()
  {
    return fixedIncome;
  }

  public void setFixedIncome(double fixedIncome)
  {
    this.fixedIncome = fixedIncome;
  }

  public double getTotalPayments()
  {
    return totalPayments;
  }

  public void setTotalPayments(double totalPayments)
  {
    this.totalPayments = totalPayments;
  }

  public double getTotalIncome()
  {
    return totalIncome;
  }

  public void setTotalIncome(double totalIncome)
  {
    this.totalIncome = totalIncome;
  }
}
