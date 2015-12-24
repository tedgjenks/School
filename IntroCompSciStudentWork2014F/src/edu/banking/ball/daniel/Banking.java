package edu.banking.ball.daniel;


import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking 
{
	
	@Override
	public void performMaintenance(int days) 
	{
		if (days > 0)
		{
			Customer temp = new Customer("");
			SavingsAccount s = new SavingsAccount();
			CheckingAccount c = new CheckingAccount();
			for(int i = 0; i<this.getCustomers().size(); i++)
			{	
				temp = (Customer) this.getCustomers().get(i);
				overdraft(temp);
				if (temp.getSavingsAccount() != null)
				{
					s = (SavingsAccount) temp.getSavingsAccount();
					if (temp.getCheckingAccount() != null)
					{
						//remove this if troublesome//
						c = (CheckingAccount) temp.getCheckingAccount();
						//////////////////////////////
						double checkBalance = c.getBalance();
						double saveBalance = s.getBalance();
						if ((saveBalance + checkBalance) < this.getMinNoFeeCombinedBalance())
						{
							c.setBalance(c.getBalance() - this.getBankingFee());
						}
					}
					//remove if troublesome//
					else
					{
						double saveBalance = s.getBalance();
						if (saveBalance < this.getMinNoFeeCombinedBalance())
						{
							s.setBalance(s.getBalance() - this.getBankingFee());
						}
					}
					/////////////////////////
				}
				else
				{
					if (temp.getCheckingAccount() != null)
					{
						c = (CheckingAccount) temp.getCheckingAccount();
						double checkBalance = c.getBalance();
						if (checkBalance < this.getMinNoFeeCombinedBalance())
						{
							c.setBalance(c.getBalance() - this.getBankingFee());
						}
					}
				}
				
				if (temp.getSavingsAccount() != null)
				{
					s = (SavingsAccount) temp.getSavingsAccount();
					s.payInterest(days);
					s.setNumTransactions(0);
				}
				if (temp.getCheckingAccount() != null)
				{
					c = (CheckingAccount) temp.getCheckingAccount();
					c.payInterest(days);
					c.setNumberOverdrafts(0);
				}
			}
		}
	}
	
	private void overdraft(AbstractCustomer customer)
	{
		if (customer.getCheckingAccount() != null)
		{
			CheckingAccount c = (CheckingAccount) customer.getCheckingAccount();
			if (c.getNumberOverdrafts() > 0)
			{
				int temp = c.getNumberOverdrafts();
				double fee = (temp * c.getOverdraftFee());
				c.setBalance(c.getBalance() - fee);
			}
		}
	}

	public static void main(String[] args) 
	{
		
	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount c, AbstractSavingsAccount s) 
	{
		Customer x = new Customer(name, c, s);
		this.getCustomers().add(x);
	}

	@Override
	public boolean removeCustomer(String name) 
	{
		Customer x = new Customer(name);
		this.getCustomers().remove(x);
		return false;
	}


}
