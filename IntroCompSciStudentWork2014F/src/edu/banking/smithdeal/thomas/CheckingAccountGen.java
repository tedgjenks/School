package edu.banking.smithdeal.thomas;

import edu.jenks.dist.banking.Account;

import edu.jenks.dist.banking.AccountGen;

import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

    public CheckingAccountGen() {
        
    }

    public CheckingAccountGen(double balance) {
        super(balance);
        
    }

    @Override
    public double deposit(double deposit) {
        double balance = getBalance(); 
        balance += deposit; 
        setBalance(balance);
        return deposit;
    }

    @Override
    public double transfer(double arg0, Account arg1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double withdraw(double withdraw) {
        double balance = getBalance();
        if(withdraw <= balance){ 
            balance -= withdraw; 
            setBalance(balance);
            return withdraw;
            
        } else
            return 0;
        
    
    }

}