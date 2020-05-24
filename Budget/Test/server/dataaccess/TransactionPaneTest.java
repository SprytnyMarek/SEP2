package server.dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransactionPaneTest
{
  private TransactionPane transactionPane;

  @BeforeEach
  public void setup(){
    transactionPane = new InDatabaseTransaction();
  }

  @Test
  public void getEmptyList(){
    ArrayList<SpendingsInfo> spendingInfo = transactionPane.getSpendingsInfo("pawel");
    assertEquals(spendingInfo.size(), 0);
  }
}