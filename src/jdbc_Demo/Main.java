package jdbc_Demo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMOperations atmOperations = new ATMOperations();

        System.out.println("Welcome to the ATM System :)");
        System.out.println("Enter your PIN Number:");
        int pin = scanner.nextInt();

        // Authenticate user
        Account account = atmOperations.authenticate(pin);
        if (account == null) {
            System.err.println("Invalid PIN. Please try again.");
            return;
        }

        System.out.println("Welcome " + account.getName() + " | Account No: " + account.getAccountNumber());

        while (true) {
            System.out.println("Press:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atmOperations.checkBalance(account);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = scanner.nextInt();
                    atmOperations.deposit(account, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = scanner.nextInt();
                    atmOperations.withdraw(account, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atmOperations.changePin(account, newPin);
                    break;
                case 5:
                    System.out.println("Thank you! Visit again.");
                    scanner.close();
                    return;
                default:
                    System.err.println("Invalid choice. Please try again.");
            }
        }
    }
}
