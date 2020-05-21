package shared.datatransfer;

import java.io.Serializable;

public class TransactionInformation implements Serializable {
  private String description;
  private double money;

  public TransactionInformation(String description, double money) {
    this.description = description;
    this.money = money;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }
}