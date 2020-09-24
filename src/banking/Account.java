package banking;

public class Account {
    private int id;
    private String accountNumber;
    private String PIN;
    private double balance;

    public Account() {
    }
    public Account(String accountNumber, String PIN) {
        this.accountNumber = accountNumber;
        this.PIN = PIN;
        this.balance = 0.0;
    }

    public String getPIN() {
        return PIN;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }



    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }
}
