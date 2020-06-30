package com.hua.multithreads.test.orderApply;

import lombok.Data;

@Data
public class Account {

    private int id;
    private int balance;


    //按照id大小申请锁
    void transfer(Account target, int amt) {
        Account left = this;
        Account right = target;

        if (this.getId() > target.getId()) {
            left = target;
            right = this;
        }

        synchronized (left) {
            synchronized (right) {
                if (balance >= amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
