package com.java.branching.solutions.with.states;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AccountUnfrozen accountUnfrozen =  new AccountUnfrozen();
        Account account = new Account(accountUnfrozen);

        account.deposit(new BigDecimal(1000));
        System.out.println(account.getBalance());

        account.withdraw(new BigDecimal(500));
        System.out.println(account.getBalance());
    }
}
