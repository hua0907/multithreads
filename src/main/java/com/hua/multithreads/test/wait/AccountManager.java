package com.hua.multithreads.test.wait;

import com.hua.multithreads.test.transfer.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    private List<Account> accountList = new ArrayList<>();

    public synchronized void apply(Account from, Account to) {
        while (accountList.contains(from) || accountList.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        accountList.add(from);
        accountList.add(to);
    }

    public synchronized void free(Account from, Account to) {
        accountList.remove(from);
        accountList.remove(to);
        notifyAll();
    }

}
