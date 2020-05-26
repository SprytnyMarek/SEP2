package dao.transactionCategoriesDAO;

import shared.datatransfer.Transaction;
import shared.datatransfer.TransactionCategories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransactionCategoriesDAO
{
  ArrayList getStringCategories() throws SQLException;
}
