package com.java.branching.solutions.with.states;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface AccountState {
    /**
     * deposit method accepts amount, which is to be added to account balance
     * it also accepts a callback, which will be executed only in those STATES where deposit is allowed
     */
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance);

    /**
     * withdraw method accepts available balance & withdraw amount
     * it also accepts a callback, which will be executed only in those STATES where withdraw is allowed
     */
    public AccountState withdraw(BigDecimal balance, BigDecimal amount,
                                 Consumer<BigDecimal> subtractFromBalance);
    public AccountState verifyHolder();
    public AccountState freezeAccount();
    public AccountState closeAccount();
}
