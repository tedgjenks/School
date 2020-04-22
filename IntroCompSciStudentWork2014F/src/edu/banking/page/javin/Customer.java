package edu.banking.page.javin;
import edu.jenks.dist.banking.*;
public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		if(savingsAccount != null) {
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
		}
	}
	@Override
	public int compareTo(AbstractCustomer o) {
		// TODO Auto-generated method stub
		return getName().compareTo(o.getName());
	}
	public String toString() {
		return this.getName();
	}

}
