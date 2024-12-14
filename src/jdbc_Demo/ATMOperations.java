package jdbc_Demo;
public class ATMOperations {
    private final ATMDatabase database = new ATMDatabase();

    // Authenticate user by PIN
    public Account authenticate(int pin) {
        return database.getAccountByPin(pin);
    }

    // Check balance
    public void checkBalance(Account account) {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    // Deposit money
    public void deposit(Account account, int amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            database.updateBalance(account);
            System.out.println("Successfully deposited " + amount);
            System.out.println("Your Current Balance: " + account.getBalance());
        } else {
            System.err.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    public void withdraw(Account account, int amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            database.updateBalance(account);
            System.out.println("Successfully withdrew " + amount);
            System.out.println("Your Current Balance: " + account.getBalance());
        } else if (amount > account.getBalance()) {
            System.err.println("Insufficient balance.");
        } else {
            System.err.println("Invalid withdrawal amount.");
        }
    }

    // Change PIN
    public void changePin(Account account, int newPin) {
        if (String.valueOf(newPin).length() == 4) { // Ensure PIN is 4 digits
            account.setPin(newPin);
            database.updatePin(account);
            System.out.println("PIN changed successfully.");
        } else {
            System.err.println("PIN must be 4 digits.");
        }
    }
}
