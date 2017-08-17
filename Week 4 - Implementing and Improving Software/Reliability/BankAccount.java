public class BankAccount {
    private double balance = 0;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        // checks and withdrawal
    }

    public void deposit(double amount) {
        // checks and deposit
    }

    public void transfer(BankAccount other, double amount) {
        this.deposit(amount);
        other.withdraw(amount);
    }

    /**
     * Notify Caller using Error Codes
     */
    public static final int NULL_ARGUMENT = -1;
    public static final int NEGATIVE_BALANCE = -2;
    public static final int SUCCESS = 0;
    public int transferNotifyCaller(BankAccount other, double amount) {
        if (other == null) {
            return BankAccount.NULL_ARGUMENT;
        } else if (this.balance < 0) {
            return BankAccount.NEGATIVE_BALANCE;
        }
        this.deposit(amount);
        this.withdraw(amount);
        return BankAccount.SUCCESS;
    }

    /**
     * Notify caller using Unchecked Exceptions
     */
    public void transferUnchecked(BankAccount other, double amount) {
        if (other == null) {
            throw new IllegalArgumentException("Bank Account is null");
        } else if (this.balance < 0) {
            throw new IllegalStateException("Balance is negative");
        }
        this.deposit(amount);
        other.withdraw(amount);
    }

    /**
     * Notify caller using Checked Exceptions
     */
    public void transferChecked(BankAccount other, double amount)
        throws NullArgumentException, NegativeBalanceException {
        if (other == null) {
            throw new NullArgumentException();
        } else if (this.balance < 0) {
            throw new NegativeBalanceException();
        }
        this.deposit(amount);
        other.withdraw(amount);
    }

    /**
     * Notify caller using Assertions
     */
    public void transferAssertion(BankAccount other, double amount) {
        assert other != null;
        assert this.balance >= 0;
        this.deposit(amount);
        other.withdraw(amount);
    }
}

class NullArgumentException extends Exception {
    public NullArgumentException() {
        super();
    }
}

class NegativeBalanceException extends Exception {
    public NegativeBalanceException() {
        super();
    }
}
