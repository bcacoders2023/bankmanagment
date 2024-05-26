import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

public class BankManagementSystem {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        BankManagementSystem bankSystem = new BankManagementSystem();
        bankSystem.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performTransaction(scanner, "deposit");
                    break;
                case 3:
                    performTransaction(scanner, "withdraw");
                    break;
                case 4:
                    viewBalance(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.next();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully!");
    }

    private void performTransaction(Scanner scanner, String transactionType) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            if (transactionType.equals("deposit")) {
                account.deposit(amount);
                System.out.println("Deposit successful.");
            } else if (transactionType.equals("withdraw")) {
                account.withdraw(amount);
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    private void viewBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
