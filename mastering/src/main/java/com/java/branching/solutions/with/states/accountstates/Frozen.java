package com.java.branching.solutions.with.states.accountstates;

import com.java.branching.solutions.with.states.AccountState;
import com.java.branching.solutions.with.states.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class Frozen implements AccountState {
    /**
     * callback handler to notify during an account deposit/withdraw
     * which happens when a state change from Frozen to Active only
     * to inform the handler to unfroze the account
     */
    private AccountUnfrozen onUnfrozen;

    public Frozen(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    /**
     * every deposits will be accepted unconditionally
     * and state will change to 'Active' state
     */
    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return this.unfreeze();
    }

    /**
     * every withdraw will be accepted based one single condition
     * allow withdraw, if the withdraw amount is lesser than available balance
     * state will change to 'Active' state
     */
    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount,
                                 Consumer<BigDecimal> subtractFromBalance) {
        if(balance.compareTo(amount) >= 0)
            subtractFromBalance.accept(amount);
        return this.unfreeze();
    }

    /**
     * verifyHolder() keep the state in 'Frozen' only
     */
    @Override
    public AccountState verifyHolder() {
        return this;
    }

    /**
     * freezeAccount() will keep the same state 'Frozen'
     */
    @Override
    public AccountState freezeAccount() {
        return this;
    }

    /**
     * closeAccount() change the state to 'Closed'
     */
    @Override
    public AccountState closeAccount() {
        return new Closed();
    }

    private AccountState unfreeze() {
        //when an account is deposited/withdrawn, delegate the call to AccountUnfrozen to unfroze an account
        this.onUnfrozen.handle();
        return new Active(this.onUnfrozen);
    }
}
