package edu.banking.piland.will;



import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount(){

	}
	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}



	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		double e = Math.E;
		double accountInterest =  getAccountInterestAPR();
		double amountToAdd;
		amountToAdd = this.getBalance()*Math.pow(e, (accountInterest / 100) * (days/DAYS_IN_A_YEAR));
		this.setBalance(amountToAdd);
	}

	@Override
	public double transfer(Account destination, double amount) {
		double amountToWithDrawn = 0;
		if(amount <= this.getBalance()){
			amountToWithDrawn = amount;

		}
		this.setBalance(this.getBalance() - amountToWithDrawn);
		if(destination.canTransact())
			destination.deposit(amount);
		return amountToWithDrawn;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		// TODO Auto-generated method stub
		double amountToOverDraft = 0;
		double amountwithdrawn = 0;
		double amountToAddToLSA = 0;

		AbstractSavingsAccount lsa = this.getLinkedSavingsAccount();

		if(getLinkedSavingsAccount() != null && lsa.getNumTransactions() < lsa.getMaxMonthlyTransactions()){
			if(requestedWithdrawal > this.getBalance() + lsa.getBalance() && requestedWithdrawal < this.getBalance() +lsa.getBalance() + getOverdraftMax()){
				if (this.isOverdraftProtected() == true){
					amountwithdrawn = requestedWithdrawal;
					amountToOverDraft = this.getBalance() + lsa.getBalance() - amountwithdrawn;
					this.setBalance(amountToOverDraft);
				}
			}
			if((requestedWithdrawal > this.getBalance())&& requestedWithdrawal <= this.getBalance() + lsa.getBalance()){
				amountToAddToLSA = requestedWithdrawal - this.getBalance();
				System.out.println(amountToAddToLSA);
				lsa.setBalance(lsa.getBalance() - amountToAddToLSA);
				this.setBalance(this.getBalance() + amountToAddToLSA);
			}
		}

		if(requestedWithdrawal <= this.getBalance()){
			amountwithdrawn = requestedWithdrawal;
			double newBalance = this.getBalance() - amountwithdrawn;
			setBalance(newBalance);
		}

		if(requestedWithdrawal > this.getBalance() && requestedWithdrawal <= this.getBalance() + this.getOverdraftMax() && this.getLinkedSavingsAccount() == null){
			amountToOverDraft = requestedWithdrawal;
			amountwithdrawn = amountToOverDraft;
			this.setBalance(this.getBalance() - amountToOverDraft);
		}
		if( this.isOverdraftProtected()){
			if(requestedWithdrawal > this.getBalance() && this.getLinkedSavingsAccount() == null && requestedWithdrawal <= this.getBalance() +this.getOverdraftMax()){
				this.setNumberOverdrafts(getNumberOverdrafts() +1);
			}
			if( this.getLinkedSavingsAccount() != null &&requestedWithdrawal <= this.getBalance() + lsa.getBalance() + this.getOverdraftMax() && requestedWithdrawal > this.getBalance() + lsa.getBalance()){
				this.setNumberOverdrafts(getNumberOverdrafts() +1); 
			}
		}
		if(getLinkedSavingsAccount() != null && amountwithdrawn > this.getBalance() + lsa.getBalance())
			getLinkedSavingsAccount().setNumTransactions(lsa.getNumTransactions() +1);
		if(amountwithdrawn > this.getBalance() && getLinkedSavingsAccount() != null && amountwithdrawn <lsa.getBalance() + this.getBalance())
			getLinkedSavingsAccount().setNumTransactions(lsa.getNumTransactions() +1);
			
			
		return amountwithdrawn;
	}
}

