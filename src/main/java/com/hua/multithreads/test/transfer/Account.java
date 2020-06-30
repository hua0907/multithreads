package com.hua.multithreads.test.transfer;

public class Account {

    private int balance;

//   synchronized void transfer(Account target, int amt) {
//        if (balance >= amt) {
//            this.balance -= amt;
//            target.balance += amt;
//        }
//    }

    void transfer(Account target, int amt) {
        synchronized (this) {
            synchronized (target) {
                if (balance >= amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
