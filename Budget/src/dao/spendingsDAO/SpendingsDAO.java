package dao.spendingsDAO;

import shared.datatransfer.Account;
import shared.datatransfer.SpendingsInfo;

import java.sql.SQLException;

public interface SpendingsDAO
{
  SpendingsInfo create(String username, String category, double money) throws SQLException;
  void update(SpendingsInfo spendingsInfo) throws SQLException;
  void delete(SpendingsInfo spendingsInfo) throws SQLException;
  SpendingsInfo readByUsernameID(String searchString) throws  SQLException;
}
