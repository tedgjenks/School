package edu.banking.mathis.justin;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount (){

	}
	public CheckingAccount (double balance, double apr){
		super(balance, apr);
	}

	@Override
	public void payInterest(int days) {
		double a = getAccountInterestAPR()/100.0;
		double b = Math.E;
		double d = getBalance();
		double e = days/DAYS_IN_A_YEAR;
		double c = Math.pow(b, a*e);
		setBalance(c*d);

	}

	@Override
	public double withdraw(double money) {
		double balance = getBalance();
		double balance1 = balance;
		if (money <= balance){
			balance = balance - money;
			setBalance(balance);
		}
		else {
			AbstractSavingsAccount sa = getLinkedSavingsAccount();
			
			if (sa != null && sa.getBalance() >= money - balance && sa.canTransact()){
				double savingsdeduction = money - balance;
				double savingsbalance = sa.getBalance();
				sa.setBalance(savingsbalance - savingsdeduction);
				setBalance(0);
				sa.setNumTransactions(sa.getNumTransactions()+1);
			}
			else if(isOverdraftProtected()){
				if((sa == null)&& getOverdraftMax() >= money-getBalance()){
					int a = getNumberOverdrafts();
					setNumberOverdrafts(a+=1);
					setBalance(getBalance()-money);
				}
				else if(sa != null && getOverdraftMax() >= money-(getBalance()+sa.getBalance()) && sa.canTransact()){
					int a = getNumberOverdrafts();
					double savingb = getLinkedSavingsAccount().getBalance();
					setNumberOverdrafts(a+=1);
					setBalance((savingb+getBalance())-money);
					getLinkedSavingsAccount().setBalance(0);
					sa.setNumTransactions(sa.getNumTransactions()+1);
				}

			}

		}
		if (getBalance() == balance1){
			money = 0;
		}
		return money;
	}

	@Override
	public double transfer(Account acc, double amt) {
		double thisbal = this.getBalance();
		if (acc.canTransact() && this.canTransact()){
			if (amt <= thisbal){
				acc.deposit(amt);
				this.withdraw(amt);
				
			}
			else amt = 0;
		}
		else amt = 0;
		return amt;
	}
	public boolean canTransact(){
		return true;
	}

}
