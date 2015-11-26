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
		destination.setBalance(destination.getBalance() + amountToWithDrawn);
		return amountToWithDrawn;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		// TODO Auto-generated method stub
		double amountToOverDraft = 0;
		double amountwithdrawn = 0;
		double amountToAddToLSA = 0;
		AbstractSavingsAccount lsa = this.getLinkedSavingsAccount();

		if(getLinkedSavingsAccount() != null){
			if(requestedWithdrawal > this.getBalance() + lsa.getBalance()){
				if (this.isOverdraftProtected() == true){
					amountwithdrawn = requestedWithdrawal;
					amountToOverDraft = this.getBalance() + lsa.getBalance() - amountwithdrawn;
					this.setBalance(amountToOverDraft);
				}
			}
			if((requestedWithdrawal > this.getBalance()) && (requestedWithdrawal <= (this.getBalance() + lsa.getBalance()))){
				amountToAddToLSA = requestedWithdrawal - this.getBalance();
				System.out.println(amountToAddToLSA);
				lsa.setBalance(lsa.getBalance() - amountToAddToLSA);
				this.setBalance(this.getBalance() + amountToAddToLSA);
			}
		}
		if(requestedWithdrawal <= this.getBalance() + getOverdraftMax()){
			amountwithdrawn = requestedWithdrawal;
			double newBalance = this.getBalance() - amountwithdrawn;
			setBalance(newBalance);

		}
		if(requestedWithdrawal > this.getBalance())
			setNumberOverdrafts(getNumberOverdrafts() +1);
		
		return amountwithdrawn;
	}
}


