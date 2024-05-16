"""ATM INTERFACE"""
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(String accountNumber, String pin) {
        return this.pin.equals(pin) && this.accountNumber.equals(accountNumber);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    public void transfer(double amount, BankAccount recipient) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
        }
    }

    public void updatePin(String newPin) {
        this.pin = newPin;
        System.out.println("PIN updated successfully.");
    }

    public void updateAccountNumber(String newAccountNumber) {
        this.accountNumber = newAccountNumber;
        System.out.println("Account number updated successfully.");
    }
}

public class ATMSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create bank account with a sample account number and PIN
        BankAccount bankAccount = new BankAccount("1234567890", "1234");

        System.out.println("Welcome to the ATM System!");

        // Prompt user for account number and PIN
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (bankAccount.authenticate(accountNumber, pin)) {
            System.out.println("Authentication successful!");
            System.out.println();

            boolean quit = false;

            while (!quit) {
                System.out.println("ATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Update PIN");
                System.out.println("6. Update Account Number");
                System.out.println("7. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        bankAccount.displayTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        bankAccount.withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        bankAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's account number: ");
                        String recipientAccountNumber = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        BankAccount recipient = new BankAccount(recipientAccountNumber, "");
                        bankAccount.transfer(transferAmount, recipient);
                        break;
                    case 5:
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.nextLine();
                        bankAccount.updatePin(newPin);
                        break;
                    case 6:
                        System.out.print("Enter new account number: ");
                        String newAccountNumber = scanner.nextLine();
                        bankAccount.updateAccountNumber(newAccountNumber);
                        break;
                    case 7:
                        quit = true;
                        System.out.println("Thank you for using the ATM system!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }
        } else {
            System.out.println("Invalid account number or PIN. Access denied!");
        }

        scanner.close();
    }
}
