package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.AbstractCustomer;

public class Banking extends AbstractBanking 
{

	public Banking() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performMaintenance(int arg0) 
	{
	
	}
	
	private void bankingFees (AbstractCustomer cust)
	{
		
	}
	
	private void overdraftFee(int numOverdrafts)
	{
		getCustomers();
	}
	
	private void continuousCompounding()
	{
		
	}
	
	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeCustomer(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
