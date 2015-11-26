package edu.banking.ball.daniel;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer 
{

	public Customer(String name) 
	{
		super(name);
	}

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) 
	{
		super(name, checkingAccount, savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer other) 
	{
		String thisString = this.toString();
		String otherString = other.toString();
		int tempInt = thisString.compareTo(otherString);
		return tempInt;
	}
	
	@Override
	public boolean equals(java.lang.Object customer)
	{
		String thisName = this.getName();
		String otherName = customer.toString();
		if (thisName.equals(otherName))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		String name = this.getName();
		return name;
	}

}
