package com.hua.multithreads.test.transfer;

public class TestAccountManager {

    public static void main(String[] args) {
        AccountManager manager = new AccountManager();

        Account source = new Account();
        Account target = new Account();

        while (manager.apply(source, target)) ;

        try {
            synchronized (source) {
                synchronized (target) {
                    source.transfer(target, 100);
                }
            }
        } finally {
            manager.free(source, target);
        }


    }
}
