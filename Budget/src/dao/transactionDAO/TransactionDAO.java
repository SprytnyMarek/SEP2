package dao.transactionDAO;

import shared.datatransfer.Transaction;

import java.sql.SQLException;

public interface TransactionDAO {
    Transaction create(String adminLogIn, String username, String category_code, double amountOfMoney, double payments,String description) throws SQLException;
    void update (Transaction transaction) throws  SQLException;
    void delete (Transaction transaction) throws  SQLException;
    Transaction readByUsernameID(String searchString) throws  SQLException;
}
