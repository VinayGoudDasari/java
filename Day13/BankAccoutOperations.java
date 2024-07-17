package Day13;

import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}

class TransactionThread extends Thread {
    private BankAccount account;
    private double amount;
    private boolean isDeposit;

    public TransactionThread(BankAccount account, double amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

 class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(initialBalance);

        System.out.print("Enter number of transactions: ");
        int numTransactions = scanner.nextInt();

        for (int i = 0; i < numTransactions; i++) {
            System.out.print("Enter amount for transaction " + (i + 1) + " (negative for withdrawal, positive for deposit): ");
            double amount = scanner.nextDouble();

            boolean isDeposit = amount > 0;
            TransactionThread thread = new TransactionThread(account, Math.abs(amount), isDeposit);
            thread.start();
        }

        try {
            Thread.sleep(1000); // wait for all transactions to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
