package com.java.branching.solutions.with.states.accountstates;

import com.java.branching.solutions.with.states.AccountState;
import com.java.branching.solutions.with.states.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NotVerified implements AccountState {
    private AccountUnfrozen onUnfrozen;

    public NotVerified(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    /**
     * 'NotVerified' state should accept the deposit, but should remain in the same state
     * by using java callback here, we can eliminate the uses of code flow checks/branch
     */
    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return this;
    }

    /**
     * withdraw is not allowed in 'NonVerified' state, and it will remains in the same state
     */
    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount,
                                 Consumer<BigDecimal> subtractFromBalance) {
        return this;
    }

    /**
     * once the account is verified, this state will change to 'Active'
     */
    @Override
    public AccountState verifyHolder() {
        return new Active(this.onUnfrozen);
    }

    /**
     * freezeAccount() has no effects in 'NonVerified' state
     * if it requires, we can introduce another new state like 'FrozenNotVerified'
     * and can return 'new FrozenNotVerified()'
     */
    @Override
    public AccountState freezeAccount() {
        return this;
    }

    /**
     * once the account is closed, this state will change to 'Closed'
     */
    @Override
    public AccountState closeAccount() {
        return new Closed();
    }
}
