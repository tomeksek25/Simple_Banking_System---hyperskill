package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AccountRepository {
    String url;
    SQLiteDataSource dataSource;

    public AccountRepository(String url) {
        this.url = url;
        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try {
            createTable();
        } catch (NullPointerException e) {
            System.out.println("Wrong file name");
            System.exit(1);
        }

    }

    private void createTable() {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "number TEXT NOT NULL," +
                        "pin TEXT NOT NULL," +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Account account) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("INSERT INTO card VALUES(" +
                        account.getId() + "," +
                        account.getAccountNumber() + "," +
                        account.getPIN() + "," +
                        account.getBalance() + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Account> verify(String number, String PIN) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet accounts = statement.executeQuery("SELECT * FROM card")) {
                    while (accounts.next()) {
                        if (accounts.getString("number").equals(number)
                        && accounts.getString("pin").equals(PIN)) {
                            Account account = new Account(number, PIN);
                            account.setId(accounts.getInt("id"));
                            account.setBalance(accounts.getInt("balance"));
                            return Optional.of(account);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(Account account) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("DELETE FROM card WHERE id =" +
                        account.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean changeBalance(String number, int money) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("UPDATE card SET balance = balance + " +
                        money +
                        " WHERE number = " +
                        number);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public int generateId() {
        int i = 0;
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet accounts = statement.executeQuery("SELECT * FROM card")) {
                    while (accounts.next()) {
                        i++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



}
