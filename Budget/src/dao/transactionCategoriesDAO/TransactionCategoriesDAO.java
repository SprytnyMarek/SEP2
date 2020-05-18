package dao.transactionCategoriesDAO;

import shared.datatransfer.Transaction;
import shared.datatransfer.TransactionCategories;

import java.sql.SQLException;

public interface TransactionCategoriesDAO
{
  TransactionCategories create(String category_code, String category_description, String parent_category) throws SQLException;
  void update (TransactionCategories transactionCategories) throws  SQLException;
  void delete (TransactionCategories transactionCategories) throws  SQLException;
  TransactionCategories readByUsernameID(String searchString) throws  SQLException;
}
