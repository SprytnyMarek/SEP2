package dao.spendingsDAO;

import shared.datatransfer.Account;
import shared.datatransfer.SpendingsInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpendingsDAO
{
  SpendingsInfo create(String username, String category, double money) throws SQLException;
  void update(SpendingsInfo spendingsInfo) throws SQLException;
  void delete(SpendingsInfo spendingsInfo) throws SQLException;
  ArrayList<SpendingsInfo> readByUsernameID(String searchString) throws  SQLException;
}
