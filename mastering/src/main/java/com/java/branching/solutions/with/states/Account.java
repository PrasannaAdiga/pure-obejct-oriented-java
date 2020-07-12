package com.java.branching.solutions.with.states;

import com.java.branching.solutions.with.states.accountstates.Active;

import java.math.BigDecimal;

public class Account {
    //managing the balance is only the responsibility of Account
    private BigDecimal balance;
    private AccountState state;

    //public constructor, which initialize the balance to zero
    //by default, creates an account in Active state
    //injects the AccountUnfrozen object
    public Account(AccountUnfrozen onUnfrozen) {
        this.balance = BigDecimal.ZERO;
        this.state = new Active(onUnfrozen);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        this.state = this.state.deposit(amount, this::addToBalance);
    }

    private void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.state = this.state.withdraw(this.balance, amount, this::subtractFromBalance);
    }

    private void subtractFromBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void verifyHolder() {
        this.state = this.state.verifyHolder();
    }

    public void freezeAccount() {
        this.state = this.state.freezeAccount();
    }

    public void closeAccount() {
        this.state = this.state.closeAccount();
    }

}
