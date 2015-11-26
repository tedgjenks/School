package edu.banking.busbee.hunter;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount() {

	}

	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);

	}

	@Override
	public void payInterest(int days) {
		double e = Math.E;
		double aPR = getAccountInterestAPR();
		double accountBalance = getBalance()* Math.pow(e, (aPR * (days / DAYS_IN_A_YEAR)));
		setBalance(accountBalance);

	}

	@Override
	public double transfer(Account destination, double amount) {
		double balance = getBalance();
		if(canTransact() && balance >= amount){
			this.withdraw(amount);
			setBalance(balance - amount);
			destination.deposit(amount);
		}
		else {
			amount = 0;
		}
		return amount;
	}
	private double linkedWithdrawal(double requestedWithdrawal){
		AbstractSavingsAccount linkedSavings = getLinkedSavingsAccount();
		double savingsMoney = getBalance() - requestedWithdrawal;
		setBalance(0);
		linkedSavings.withdraw(savingsMoney);
		setLinkedSavingsAccount(linkedSavings);
		return requestedWithdrawal;
	}


	private double overdraftWithdrawal(double requestedWithdrawal){
		double balance = getBalance(), accountInterestAPR = getAccountInterestAPR();
		AbstractSavingsAccount linkedSavings = getLinkedSavingsAccount();
		CheckingAccount checkingAccount = new CheckingAccount(balance, accountInterestAPR);
		int numOfOverdrafts = getNumberOverdrafts();
		if(linkedSavings == null){
			double overdraftMoney = requestedWithdrawal - checkingAccount.getBalance();
			
			setBalance(- overdraftMoney);
		}
		double overdraftMoney = requestedWithdrawal - (checkingAccount.getBalance() + linkedSavings.getBalance());
		if(checkingAccount.getOverdraftMax() >= overdraftMoney){
			double overdraftBalance = getOverdraftMax() - overdraftMoney;
			numOfOverdrafts ++;
			setBalance(- overdraftBalance);
		}
		return requestedWithdrawal;
	}


	@Override
	public double withdraw(double requestedWithdrawal) {
		double balance = getBalance();
		AbstractSavingsAccount linkedSavings = getLinkedSavingsAccount();
		if(balance >= requestedWithdrawal){
			setBalance(balance - requestedWithdrawal);
			return requestedWithdrawal;
		}
		else if(linkedSavings != null && linkedSavings.canTransact()){
			if(linkedSavings.getBalance() >= requestedWithdrawal){
				return linkedWithdrawal(requestedWithdrawal);
			}

			else if((getBalance() + linkedSavings.getBalance() < requestedWithdrawal)){
				if(linkedSavings.canTransact()){
					return overdraftWithdrawal(requestedWithdrawal);
				}
				else 
					return 0;
			}


			else if(getBalance() < requestedWithdrawal){
				if(getOverdraftMax() + getBalance() >= requestedWithdrawal){
					return overdraftWithdrawal(requestedWithdrawal);
				}
				else 
					return 0;
			}
			else 
				return 0;


		}
		else{
			return 0.0;

		}
	}

}






