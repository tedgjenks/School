package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.AbstractCustomer;

public class Banking extends AbstractBanking 
{

	public Banking() 
	{
	}

	@Override
	public void performMaintenance(int days) 
	{
		for(AbstractCustomer c: getCustomers())
		{
			overdraftFee(c);
			bankingFees(c);
			negBal(c);
			continuousCompounding(c, days);
			resetTransactionsAndOverdrafts(c);
		}
	}

	private void bankingFees (AbstractCustomer cust)
	{
		double combinedBalance = 0.0;
		AbstractCheckingAccount ca = cust.getCheckingAccount();
		AbstractSavingsAccount sa = cust.getSavingsAccount();

		if (ca != null)
			combinedBalance += ca.getBalance();
		if (sa != null)
			combinedBalance += sa.getBalance();

		if (combinedBalance < getMinNoFeeCombinedBalance())
		{
			if (ca != null)
				ca.setBalance(ca.getBalance() - getBankingFee()); 
			else if(sa != null)
				sa.setBalance(sa.getBalance() - getBankingFee());
		}


	}

	private void overdraftFee(AbstractCustomer cust)
	{
		AbstractCheckingAccount ca = cust.getCheckingAccount();
		if(cust.getCheckingAccount() != null)
		{
			ca.setBalance(ca.getBalance()-(ca.getNumberOverdrafts() * ca.getOverdraftFee()));
		}

	}

	private void continuousCompounding(AbstractCustomer cust, int days)
	{
		AbstractSavingsAccount sa = cust.getSavingsAccount();
		AbstractCheckingAccount ca = cust.getCheckingAccount();

		if (ca != null)
			ca.payInterest(days);
		if (sa != null)
			sa.payInterest(days);
	}


	private void negBal(AbstractCustomer cust)
	{
		AbstractSavingsAccount sa = cust.getSavingsAccount();
		AbstractCheckingAccount ca = cust.getCheckingAccount();
		if(ca != null && sa != null)
			if (ca.getBalance() < 0 )
			{
				ca.setBalance(ca.getBalance() + sa.getBalance());
				sa.setBalance(0.0);
				if(ca.getBalance() > 0){
					sa.setBalance(ca.getBalance());
					ca.setBalance(0.0);
				}
			}		
	}
	
	private void resetTransactionsAndOverdrafts(AbstractCustomer cust)
	{
		if(cust.getCheckingAccount() != null)
			cust.getCheckingAccount().setNumberOverdrafts(0);
		if(cust.getSavingsAccount() != null)
			cust.getSavingsAccount().setNumTransactions(0);
	}
	@Override
	public void addCustomer(String name, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) 
	{
		getCustomers().add(new Customer(name, arg1, arg2));

	}

	@Override
	public boolean removeCustomer(String name) {
		for(AbstractCustomer cust: getCustomers())
			if(cust.compareTo(new Customer(name))==0)
			{
				getCustomers().remove(cust);
				return true;
			}
		return false;
	}

}
