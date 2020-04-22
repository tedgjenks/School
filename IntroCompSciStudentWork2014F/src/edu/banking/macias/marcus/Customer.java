package edu.banking.macias.marcus;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;
public class Customer extends AbstractCustomer{

	public Customer(String name) {
		super(name);
		
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name);
		setCheckingAccount(checkingAccount);
		setSavingsAccount(savingsAccount);
	}
	public boolean equals(Object cust) {
		
		
		if(getName().equals(((AbstractCustomer)cust).getName())) {
			return true;
		}
		return false;
	}
	public int compareTo(AbstractCustomer o) {
		return getName().compareTo(o.getName());
			
		
		//return 0;
	}

}
