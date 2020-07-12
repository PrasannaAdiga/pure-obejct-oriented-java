package com.java.branching.solutions.with.states.accountstates;

import com.java.branching.solutions.with.states.AccountState;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * Whatever action happens on closed account, it remains in the same state
 * Hence each action will just return the same object reference('this') without modifying any
 */
public class Closed implements AccountState {
    /**
     * 'Closed' state should not accept the deposit
     *  hence, we should not call the callback method here
     *  state will remains in the 'Closed' state
     */
    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        return this;
    }

    /**
     * withdraw() does nothing, and returns the same state 'Closed'
     */
    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount,
                                 Consumer<BigDecimal> subtractFromBalance) {
        return this;
    }

    /**
     * verifyHolder() does nothing, and returns the same state 'Closed'
     */
    @Override
    public AccountState verifyHolder() {
        return this;
    }

    /**
     * freezeAccount() does nothing, and returns the same state 'Closed'
     */
    @Override
    public AccountState freezeAccount() {
        return this;
    }

    /**
     * closeAccount() does nothing, and returns the same state 'Closed'
     */
    @Override
    public AccountState closeAccount() {
        return this;
    }
}
