package edu.banking.tran.don;

import edu.jenks.dist.banking.*;

public class Banking extends AbstractBanking {
	public Banking() {
	}

	public static void main(String[] args) {
		CheckingAccount what = new CheckingAccount(-100.0, .5);
	}
	public void addCustomer(String name, AbstractCheckingAccount checkAct, AbstractSavingsAccount savAct) {
		getCustomers().add(new Customer(name, checkAct, savAct));
	}

	public void performMaintenance(int days) {
		AbstractCustomer temp = getCustomers().get(0);
		for(int i = 0; i < getCustomers().size(); i++) {
			temp = getCustomers().get(i);
			if(getCustomers().get(i).getCheckingAccount() == null) {
				if(temp.getSavingsAccount().getBalance() < getMinNoFeeCombinedBalance()) {
					temp.getSavingsAccount().setBalance(temp.getSavingsAccount().getBalance() - getBankingFee());
				}
				if(temp.getSavingsAccount().getBalance() > 0) {
					temp.getSavingsAccount().payInterest(days);
				}
				temp.getSavingsAccount().setNumTransactions(0);
				continue;
			}
			if(getCustomers().get(i).getSavingsAccount() == null) {
				temp.getCheckingAccount().setBalance(temp.getCheckingAccount().getBalance() - (temp.getCheckingAccount().getNumberOverdrafts() * temp.getCheckingAccount().getOverdraftFee()));
				if(temp.getCheckingAccount().getBalance() < getMinNoFeeCombinedBalance()) {
					temp.getCheckingAccount().setBalance(temp.getCheckingAccount().getBalance() - getBankingFee());
				}
				if(temp.getCheckingAccount().getBalance() > 0) {
					temp.getCheckingAccount().payInterest(days);
				}
				
				temp.getCheckingAccount().setNumberOverdrafts(0);
				continue;
			}
			if(temp.getCheckingAccount().getBalance() < 0) {
				double tempBal = Math.abs(temp.getCheckingAccount().getBalance());
				if(tempBal <= temp.getSavingsAccount().getBalance()) {
					temp.getSavingsAccount().setBalance(temp.getSavingsAccount().getBalance() - tempBal);
					temp.getCheckingAccount().setBalance(0.0);
				}
				if(tempBal > temp.getSavingsAccount().getBalance()) {
					tempBal -= temp.getSavingsAccount().getBalance();
					temp.getSavingsAccount().setBalance(0);
					temp.getCheckingAccount().setBalance(0.0 - tempBal);
				}
			}
			
			temp.getCheckingAccount().setBalance(temp.getCheckingAccount().getBalance() - (temp.getCheckingAccount().getNumberOverdrafts() * temp.getCheckingAccount().getOverdraftFee()));
			if(temp.getCheckingAccount().getBalance() < 0) {
				double tempBal = Math.abs(temp.getCheckingAccount().getBalance());
				if(tempBal <= temp.getSavingsAccount().getBalance()) {
					temp.getSavingsAccount().setBalance(temp.getSavingsAccount().getBalance() - tempBal);
					temp.getCheckingAccount().setBalance(0.0);
				}
				if(tempBal > temp.getSavingsAccount().getBalance()) {
					tempBal -= temp.getSavingsAccount().getBalance();
					temp.getSavingsAccount().setBalance(0);
					temp.getCheckingAccount().setBalance(0.0 - tempBal);
				}
			}
			if((temp.getCheckingAccount().getBalance() + temp.getSavingsAccount().getBalance()) < getMinNoFeeCombinedBalance()) {
				temp.getCheckingAccount().setBalance(temp.getCheckingAccount().getBalance() - getBankingFee());
			}
			if(temp.getCheckingAccount().getBalance() < 0) {
				double tempBal = Math.abs(temp.getCheckingAccount().getBalance());
				if(tempBal <= temp.getSavingsAccount().getBalance()) {
					temp.getSavingsAccount().setBalance(temp.getSavingsAccount().getBalance() - tempBal);
					temp.getCheckingAccount().setBalance(0.0);
				}
				if(tempBal > temp.getSavingsAccount().getBalance()) {
					tempBal -= temp.getSavingsAccount().getBalance();
					temp.getSavingsAccount().setBalance(0);
					temp.getCheckingAccount().setBalance(0.0 - tempBal);
				}
			}
			if(temp.getCheckingAccount().getBalance() > 0) {
				temp.getCheckingAccount().payInterest(days);
			}

			temp.getCheckingAccount().setNumberOverdrafts(0);
			if(temp.getSavingsAccount().getBalance() > 0) {
				temp.getSavingsAccount().payInterest(days);
			}
			//temp.getSavingsAccount().payInterest(days);
			temp.getSavingsAccount().setNumTransactions(0);
		}
	}

	public boolean removeCustomer(String name) {
		if(getCustomers().contains(name)) {
			getCustomers().remove(name);
			return true;
		}
		return false;
	}

}
