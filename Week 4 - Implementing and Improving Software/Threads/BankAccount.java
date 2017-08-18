import sun.rmi.runtime.Log;

/**
 *  Bad example of threads
 */
class BankAccount {
    private double balance = 0;

    /**
     * Here, the whole method is synchronized so the entire method is slowed down or made to wait.
     * @param balance
     */
    public synchronized void deposit(double balance) {
        this.balance+=balance;
    }

    /**
     * Here, the method is only synchronized in the place where race condition could occur
     * @param amount
     */
    public void deposit2(double amount) {
        if (amount < 0) {
            return;
        }
        synchronized (this) {
            balance += amount;
        }

    }

    public double getBalance() {
        return balance;
    }
}


class MyThread1 extends Thread {
    private BankAccount account;

    public MyThread1(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.deposit(100);
    }
}


class MyThread2 extends Thread {
    private BankAccount account;

    public MyThread2(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.deposit(200);
    }
}


class MainBank {
    public static void main(String[] args) throws InterruptedException {
        BankAccount myAccount = new BankAccount();

        // java passes objects by reference, so now t1 & t2 share the same object
        MyThread1 t1 = new MyThread1(myAccount);
        MyThread2 t2 = new MyThread2(myAccount);

        /* Potential race condition here was avoided by changing the deposit method to synchronized.
         * Now, the deposit method called by t2 cannot start until t1 finishes calling deposit
         */
        t1.start(); // sets balance to 100
        t2.start(); // now sets balance to 200

        t1.join();
        t2.join();

        System.out.println(myAccount.getBalance()); // 200
    }
}
