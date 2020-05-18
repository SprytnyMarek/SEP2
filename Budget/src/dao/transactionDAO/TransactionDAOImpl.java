package dao.transactionDAO;


import shared.datatransfer.Account;
import shared.datatransfer.Password;
import shared.datatransfer.Transaction;

import java.sql.*;

public class TransactionDAOImpl implements TransactionDAO {

    private static TransactionDAOImpl instance;

    //make private to prevent creating different DAO classes
    private TransactionDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    //makes instance so every client access the same file
    public static synchronized TransactionDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new TransactionDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"SEP2\"",
                "postgres", Password.getPassword());
    }


    @Override
    public Transaction create(String adminLogIn, String username, String category_code, double amountOfMoney, double payments, String description) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO transaction (String adminLogIn, String username, String category_code, double amountOfMoney, double payments,String description) VALUES(?, ?, ?, ? ,? ,?) ;");
            statement.setString(1, adminLogIn);
            statement.setString(2, username);
            statement.setString(3, category_code);
            statement.setDouble(4, amountOfMoney);
            statement.setDouble(5, payments);
            statement.setString(6, description);
            statement.executeUpdate();
            return new Transaction(adminLogIn, username, category_code, amountOfMoney, payments, description);
        }
    }


    @Override
    public void update(Transaction transaction) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE transaction SET adminLogIn=?, username=?, category_code =?, amountOfMoney=?, payments=?, description=?, WHERE amountOfMoney =?");
            statement.setString(1, transaction.getAdminLogIn());
            statement.setString(2, transaction.getUsername());
            statement.setString(3, transaction.getCategory_code());
            statement.setDouble(4, transaction.getAmountOfMoney());
            statement.setDouble(5, transaction.getPayments());
            statement.setString(6, transaction.getDescription());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Transaction transaction) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM transaction WHERE amountOfMoney = ?");
            statement.setString(1, transaction.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public Transaction readByUsernameID(String searchString) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM transaction WHERE amountOfMoney = ? ");
            statement.setString(1, searchString);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                String adminLogIn = resultSet.getString("adminLogIn");
                String username = resultSet.getString("username");
                String category_code = resultSet.getString("category_code");
                double amountOfMoney = resultSet.getDouble("amountOfMoney");
                double payments = resultSet.getDouble("payments");
                String description = resultSet.getString("description");
               Transaction transaction = new Transaction(adminLogIn,username, category_code, amountOfMoney, payments, description);
                return transaction;
            }
            else
            {
                return null;
            }
        }
    }
}
