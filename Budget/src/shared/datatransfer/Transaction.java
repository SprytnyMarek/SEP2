package shared.datatransfer;

public class Transaction {
    private String adminLogIn;
    private String username;
    private String category_code;
    private double amountOfMoney;
    private double payments;
    private String description;

    public Transaction(String adminLogIn, String username, String category_code, double amountOfMoney, double payments, String description) {
        this.adminLogIn = adminLogIn;
        this.username = username;
        this.category_code = category_code;
        this.amountOfMoney = amountOfMoney;
        this.payments = payments;
        this.description = description;
    }

    public String getAdminLogIn() {
        return adminLogIn;
    }

    public void setAdminLogIn(String adminLogIn) {
        this.adminLogIn = adminLogIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
