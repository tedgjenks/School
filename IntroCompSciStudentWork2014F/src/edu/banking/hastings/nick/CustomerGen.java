package edu.banking.hastings.nick;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
    private CheckingAccount checkingaccount;
    private SavingsAccount savingsaccount;
    private String name;
   
    public CustomerGen(String name) {
        this.name = name;
    }

    @Override
    public void addCheckingAccount(CheckingAccount checkingaccount) {
        this.checkingaccount = checkingaccount;
    }

    @Override
    public void addSavingsAccount(SavingsAccount savingsaccount) {
        this.savingsaccount = savingsaccount;
    }

    @Override
    public CheckingAccount getCheckingAccount() {
        return checkingaccount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SavingsAccount getSavingsAccount() {
        return savingsaccount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}