package com.java.branching.issues;

import java.math.BigDecimal;

/**
 * This account manages balance, closing, verification, freezing by itself through code block branching
 * PROBLEM: As more and more requirements comes in, more and more flags and branches gets added in the same class
 * This will cause maintaining of this class nightmare. Even requires to add more and more test cases
 */
public class AccountWithBranching {
    /**
     * account active/closed flag
     * if account is closed, then user can not deposit/withdraw money
     */
    private boolean isAccountClosed;

    /**
     * account holder verifier flag
     * if account holder is not verified, then user can not deposit/withdraw money
     */
    private boolean isAccountHolderVerified;

    /**
     * Flag to indicate whether an account is frozen, since it's not been used since long time
     */
    private boolean isFrozen;

    private BigDecimal balance;

    //listener to perform some operations, once the account is unfroze back
    private AccountUnfrozen onUnfrozen;

    //public constructor, which initialize the balance to zero
    //injects the AccountUnfrozen object
    public AccountWithBranching(AccountUnfrozen onUnfrozen) {
        this.balance = BigDecimal.ZERO;
        this.onUnfrozen = onUnfrozen;
    }

    //method to make account holder as verified
    public void setAccountHolderVerified() {
        isAccountHolderVerified = true;
    }

    //method to change an account to frozen state
    public void freezeAccount() {
        if(this.isAccountClosed)
            return; //account must not be closed
        if(!this.isAccountHolderVerified)
            return; //account holder must be verified
        this.isFrozen = true;
    }

    //method to make account as closed
    public void closeAccount() {
        isAccountHolderVerified = true;
    }

    public void deposit(BigDecimal amount) {
        //branching for account active check
        if(isAccountClosed)
            return; //if account is closed, simply not allow to deposit money
        //one more branching for account holder verification check
        if(!isAccountHolderVerified)
            return; //if account holder is not verified, simply not allow to deposit money
        //deposit money
        this.balance = this.balance.add(amount);
        //once the money is deposited successfully, unfroze the account, if in case it was frozen before
        //one more branch to handle this
        if(this.isFrozen) {
            this.isFrozen = false;
            this.onUnfrozen.handle();
        }
    }

    public void withdraw(BigDecimal amount) {
        //branching for account active check
        if(isAccountClosed)
            return; //if account is closed, simply not allow to withdraw money
        //one more branching for account holder verification check
        if(!isAccountHolderVerified)
            return; //if account holder is not verified, simply not allow to withdraw money
        //withdraw money, existing balance check logic is skipped for simplicity
        this.balance.subtract(amount);
        //once the money is withdrawn successfully, unfroze the account, if in case it was frozen before
        //one more branch to handle this
        if(this.isFrozen) {
            this.isFrozen = false;
            this.onUnfrozen.handle();
        }
    }
}
