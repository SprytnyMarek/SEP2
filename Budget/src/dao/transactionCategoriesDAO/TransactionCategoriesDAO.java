package dao.transactionCategoriesDAO;

import shared.datatransfer.Transaction;
import shared.datatransfer.TransactionCategories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransactionCategoriesDAO
{
  TransactionCategories create(int id, String title) throws SQLException;
  void update (TransactionCategories transactionCategories) throws  SQLException;
  void delete (TransactionCategories transactionCategories) throws  SQLException;
  TransactionCategories readByUsernameID(String searchString) throws  SQLException;
  ArrayList getStringCategories() throws SQLException;
}
