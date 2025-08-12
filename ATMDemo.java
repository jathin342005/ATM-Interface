/**
 * ATM Demo Class - Demonstrates all ATM functionality
 * This class simulates user interactions to show how the ATM system works
 */
public class ATMDemo {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   JAVA ATM SYSTEM - DEMONSTRATION");
        System.out.println("===========================================");
        System.out.println();
        
        // Create ATM components
        TransactionHistory history = new TransactionHistory();
        
        // Initialize accounts (same as ATM.java)
        Account account1 = new Account("ACC001", "1234", "John Doe", 2450.75);
        Account account2 = new Account("ACC002", "5678", "Jane Smith", 1200.50);
        Account account3 = new Account("ACC003", "9999", "Test User", 5000.00);
        
        System.out.println("📋 SAMPLE ACCOUNTS INITIALIZED:");
        System.out.println("- ACC001/1234 (John Doe - $2,450.75)");
        System.out.println("- ACC002/5678 (Jane Smith - $1,200.50)");
        System.out.println("- ACC003/9999 (Test User - $5,000.00)");
        System.out.println();
        
        // Simulate login
        System.out.println("🔐 SIMULATING LOGIN:");
        System.out.println("Account ID: ACC001");
        System.out.println("PIN: 1234");
        boolean loginResult = account1.validatePin("1234");
        System.out.println("Login successful: " + (loginResult ? "✅ YES" : "❌ NO"));
        System.out.println("Welcome, " + account1.getAccountHolderName() + "!");
        System.out.println();
        
        // Demonstrate balance inquiry
        System.out.println("💰 BALANCE INQUIRY:");
        System.out.printf("Current Balance: $%.2f%n", account1.getBalance());
        System.out.println();
        
        // Demonstrate deposit
        System.out.println("💵 DEPOSIT OPERATION:");
        double depositAmount = 500.00;
        double balanceBefore = account1.getBalance();
        account1.deposit(depositAmount);
        Transaction depositTx = new Transaction(
            account1.getAccountId(), "DEPOSIT", depositAmount, 
            balanceBefore, account1.getBalance()
        );
        history.addTransaction(depositTx);
        System.out.printf("Deposited: $%.2f%n", depositAmount);
        System.out.printf("New Balance: $%.2f%n", account1.getBalance());
        System.out.println();
        
        // Demonstrate withdrawal
        System.out.println("🏧 WITHDRAWAL OPERATION:");
        double withdrawAmount = 200.00;
        balanceBefore = account1.getBalance();
        account1.withdraw(withdrawAmount);
        Transaction withdrawTx = new Transaction(
            account1.getAccountId(), "WITHDRAWAL", withdrawAmount, 
            balanceBefore, account1.getBalance()
        );
        history.addTransaction(withdrawTx);
        System.out.printf("Withdrawn: $%.2f%n", withdrawAmount);
        System.out.printf("New Balance: $%.2f%n", account1.getBalance());
        System.out.println();
        
        // Demonstrate transfer
        System.out.println("💸 TRANSFER OPERATION:");
        double transferAmount = 300.00;
        double fromBalanceBefore = account1.getBalance();
        double toBalanceBefore = account2.getBalance();
        
        account1.withdraw(transferAmount);
        account2.deposit(transferAmount);
        
        Transaction transferOutTx = new Transaction(
            account1.getAccountId(), "TRANSFER_OUT", transferAmount, 
            fromBalanceBefore, account1.getBalance(), "To: " + account2.getAccountId()
        );
        Transaction transferInTx = new Transaction(
            account2.getAccountId(), "TRANSFER_IN", transferAmount, 
            toBalanceBefore, account2.getBalance(), "From: " + account1.getAccountId()
        );
        
        history.addTransaction(transferOutTx);
        history.addTransaction(transferInTx);
        
        System.out.printf("Transferred: $%.2f%n", transferAmount);
        System.out.printf("From: %s (%s)%n", account1.getAccountId(), account1.getAccountHolderName());
        System.out.printf("To: %s (%s)%n", account2.getAccountId(), account2.getAccountHolderName());
        System.out.printf("%s new balance: $%.2f%n", account1.getAccountId(), account1.getBalance());
        System.out.printf("%s new balance: $%.2f%n", account2.getAccountId(), account2.getBalance());
        System.out.println();
        
        // Display transaction history
        System.out.println("📊 TRANSACTION HISTORY:");
        history.displayHistory(account1.getAccountId());
        System.out.println();
        
        // Show all accounts final state
        System.out.println("🏦 FINAL ACCOUNT STATES:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println();
        
        // Demonstrate error handling
        System.out.println("🛡️  ERROR HANDLING DEMONSTRATION:");
        
        // Invalid PIN
        System.out.println("Testing invalid PIN:");
        boolean invalidLogin = account1.validatePin("0000");
        System.out.println("Login with wrong PIN: " + (invalidLogin ? "✅ SUCCESS" : "❌ FAILED (as expected)"));
        
        // Insufficient funds
        System.out.println("Testing insufficient funds:");
        double originalBalance = account1.getBalance();
        boolean largeWithdrawal = account1.withdraw(10000.00);
        System.out.println("Withdraw $10,000 (insufficient funds): " + (largeWithdrawal ? "✅ SUCCESS" : "❌ FAILED (as expected)"));
        System.out.printf("Balance unchanged: $%.2f%n", account1.getBalance());
        
        // Negative amount
        System.out.println("Testing negative deposit:");
        boolean negativeDeposit = account1.deposit(-100.00);
        System.out.println("Deposit -$100: " + (negativeDeposit ? "✅ SUCCESS" : "❌ FAILED (as expected)"));
        
        System.out.println();
        System.out.println("===========================================");
        System.out.println("   DEMONSTRATION COMPLETED SUCCESSFULLY");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("To run the interactive version on Windows:");
        System.out.println("1. Open Command Prompt or PowerShell");
        System.out.println("2. Navigate to this directory");
        System.out.println("3. Run: java Main");
        System.out.println("4. Or double-click run.bat");
    }
}