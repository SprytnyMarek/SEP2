package dao.accountDAO;

import shared.datatransfer.Account;

import java.sql.SQLException;

public interface AccountDAO
{
  Account create(String username, double balance, double fixedPayments, double fixedIncome, double totalPayments, double totalIncome) throws SQLException;
  void update(Account account) throws SQLException;
  void delete(Account account) throws SQLException;
  Account readByUsernameID(String searchString) throws  SQLException;
}
