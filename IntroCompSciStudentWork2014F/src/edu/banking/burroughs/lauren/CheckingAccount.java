package edu.banking.burroughs.lauren;


import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount(double balance, double accountInterestAPR){
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

	public void payInterest(int days) {
		double Interest;
		double years = days / DAYS_IN_A_YEAR;
		Interest = getBalance() * Math.pow(Math.E, (getAccountInterestAPR() / 100) * (years));
		setBalance(Interest);
	}


	public double transfer(Account desination, double amount) {		
		if(getBalance() >= amount){
			setBalance(getBalance() - amount);
			desination.setBalance(desination.getBalance() + amount);
			return amount;
			}
		else
			return 0.0;
	}
	public boolean willTransact(double requestedWithdrawal){
		AbstractSavingsAccount SA = getLinkedSavingsAccount();
		double total = getBalance() + getOverdraftMax();
		if(requestedWithdrawal <= total)
			return true;
		else{
			if(this.getLinkedSavingsAccount() != null){
				if(requestedWithdrawal <= total + SA.getBalance())
					return true;
				
			}
		}
		return false;
	}


	public double withdraw(double requestedWithdrawal) {
		if(willTransact(requestedWithdrawal)==true){
			if(requestedWithdrawal <= getBalance()){
				setBalance(getBalance() - requestedWithdrawal );
				return requestedWithdrawal;	

			}
			else{

				if(this.getLinkedSavingsAccount() == null){
					if(isOverdraftProtected() == true){
						if(getBalance() + getOverdraftMax() > requestedWithdrawal){
							setNumberOverdrafts(getNumberOverdrafts() + 1);
							setBalance(getBalance() - requestedWithdrawal );							
							return requestedWithdrawal;
						}
						else
							return 0.0;
					}
					else
						return 0.0;
				} 
				else if(getBalance() + getLinkedSavingsAccount().getBalance() >= requestedWithdrawal){
					AbstractSavingsAccount SA = getLinkedSavingsAccount();
					double leftOver = requestedWithdrawal - getBalance();
					double takeSavings = SA.getBalance() - leftOver;
					double taken = SA.getBalance() - takeSavings;
					SA.setBalance(takeSavings);
					setBalance(taken + getBalance());
					double withDrawal = getBalance() - requestedWithdrawal;
					setBalance(withDrawal);
					return requestedWithdrawal;
				}
				else {
					setBalance(getBalance() + getLinkedSavingsAccount().getBalance());
					if(isOverdraftProtected() == true){
						if(getBalance() + getOverdraftMax() > requestedWithdrawal){
							getLinkedSavingsAccount().setBalance(0);
							setBalance(getBalance() - requestedWithdrawal);
							setNumberOverdrafts(getNumberOverdrafts() + 1);
							return requestedWithdrawal;
						}

						else 
							return 0.0;	
					}
					else
						return 0.0;
				}
			}
		}
		else
			return 0.0;


	}

}

