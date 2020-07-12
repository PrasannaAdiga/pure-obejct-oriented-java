package com.java.branching.solutions.with.states.accountstates;

import com.java.branching.solutions.with.states.AccountState;
import com.java.branching.solutions.with.states.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class Active implements AccountState {
    //callback handler to notify during an account deposit/withdraw
    private AccountUnfrozen onUnfrozen;

    public Active(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    /**
     * every deposits will be accepted unconditionally
     * and state will remains in the 'Active' state only
     */
    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return this;
    }

    /**
     * every withdraw will be accepted based one single condition
     * allow withdraw, if the withdraw amount is lesser than available balance
     * state will remains in the 'Active' state
     */
    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount,
                                 Consumer<BigDecimal> subtractFromBalance) {
        if(balance.compareTo(amount) >= 0)
            subtractFromBalance.accept(amount);
        return this;
    }

    /**
     * verifyHolder() keep the state in 'Active' only
     */
    @Override
    public AccountState verifyHolder() {
        return this;
    }

    /**
     * freezeAccount() change the state to 'Frozen'
     */
    @Override
    public AccountState freezeAccount() {
        return new Frozen(this.onUnfrozen);
    }

    /**
     * closeAccount() change the state to 'Closed'
     */
    @Override
    public AccountState closeAccount() {
        return new Closed();
    }
}
