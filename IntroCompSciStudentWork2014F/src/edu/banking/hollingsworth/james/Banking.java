package edu.banking.hollingsworth.james;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {
	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		this.getCustomers().add(new Customer(name, checkingAccount, savingsAccount));
	}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer c : this.getCustomers()) {
			AbstractCheckingAccount checking = c.getCheckingAccount();
			AbstractSavingsAccount savings = c.getSavingsAccount();
			
			if(checking != null) {
				if(savings != null && checking.getBalance() < 0)
					savings.transfer(checking, (checking.getBalance()));
				checking.setBalance(checking.getBalance() - checking.getNumberOverdrafts() * checking.getOverdraftFee());
				if(savings != null && checking.getBalance() + savings.getBalance() < this.getMinNoFeeCombinedBalance())
					checking.setBalance(checking.getBalance() - this.getBankingFee());
				checking.payInterest(days);
				checking.setNumberOverdrafts(0);
			}
			if(savings != null) {
				savings.payInterest(days);
				savings.setNumTransactions(0);
			}
		}
	}

	@Override
	public boolean removeCustomer(String name) {
		boolean contains = false;
		for(AbstractCustomer c : this.getCustomers()) {
			if(c.getName().equals(name)) {
				contains = true;
				this.getCustomers().remove(c);
				break;
			}
		}
		return contains;
	}

}

