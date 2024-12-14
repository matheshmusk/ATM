package jdbc_Demo;

import java.sql.*;

public class ATMDatabase {
    private final String DB_URL = "jdbc:mysql://localhost:3306/atm";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "admin";

    // Fetch account by PIN
    public Account getAccountByPin(int pin) {
        String query = "SELECT * FROM atm WHERE pin = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int accountNumber = resultSet.getInt("acc");
                String name = resultSet.getString("acname");
                int balance = resultSet.getInt("balance");
                return new Account(accountNumber, pin, name, balance);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // Update balance in the database
    public void updateBalance(Account account) {
        String query = "UPDATE atm SET balance = ? WHERE pin = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, account.getBalance());
            preparedStatement.setInt(2, account.getPin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // Update PIN in the database
    public void updatePin(Account account) {
        String query = "UPDATE atm SET pin = ? WHERE account_no = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, account.getPin());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
