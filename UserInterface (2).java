import java.util.Scanner;

// ----------------- Account Class -----------------
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ " + amount + " deposited successfully!");
        } else {
            System.out.println("⚠ Deposit amount must be positive.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("✅ " + amount + " withdrawn successfully!");
            } else {
                System.out.println("⚠ Insufficient balance.");
            }
        } else {
            System.out.println("⚠ Invalid withdrawal amount.");
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Holder Name      : " + accountHolderName);
        System.out.println("Balance          : " + balance);
        System.out.println("Email            : " + email);
        System.out.println("Phone Number     : " + phoneNumber);
    }

    // Update email & phone
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("✅ Contact details updated successfully!");
    }
}

// ----------------- User Interface Class -----------------
public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner sc;

    public UserInterface(int size) {
        accounts = new Account[size]; // array to hold accounts
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    // Find account by account number
    private Account findAccount(int accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }

    // Create new account
    public void createAccount() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        accounts[accountCount++] = new Account(accNum, name, balance, email, phone);
        System.out.println("🎉 Account created successfully!");
    }

    // Deposit operation
    public void performDeposit() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        Account acc = findAccount(accNum);
        if (acc != null) acc.deposit(amount);
        else System.out.println("⚠ Account not found.");
    }

    // Withdraw operation
    public void performWithdrawal() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        Account acc = findAccount(accNum);
        if (acc != null) acc.withdraw(amount);
        else System.out.println("⚠ Account not found.");
    }

    // Show details
    public void showAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();

        Account acc = findAccount(accNum);
        if (acc != null) acc.displayAccountDetails();
        else System.out.println("⚠ Account not found.");
    }

    // Update contact
    public void updateContact() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter new Email: ");
            String email = sc.nextLine();
            System.out.print("Enter new Phone Number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("⚠ Account not found.");
        }
    }

    // Main Menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n=== Banking Application Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> System.out.println("👋 Exiting... Thank you!");
                default -> System.out.println("⚠ Invalid choice, try again.");
            }
        } while (choice != 6);
    }

    // Program starts here
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(10); // max 10 accounts
        ui.mainMenu();
    }
}
