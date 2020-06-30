package com.hua.multithreads.test.transfer;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    private List<Account> List = new ArrayList<>();

    public synchronized boolean apply(Account source, Account target) {
        if (!List.contains(source) && !List.contains(target)) {
            List.add(source);
            List.add(target);
            return true;
        }
        return false;
    }

    public synchronized void free(Account source, Account target) {
        List.remove(source);
        List.remove(target);
    }

}
