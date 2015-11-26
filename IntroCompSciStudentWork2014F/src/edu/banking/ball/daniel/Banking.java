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
				System.out.println("Loop: " + i);
				temp = (Customer) this.getCustomers().get(i);
				System.out.println(temp.toString());
				if (temp.getCheckingAccount() != null)
				{
					c = (CheckingAccount) temp.getCheckingAccount();
					System.out.println(c.getBalance());
					int over = c.getNumberOverdrafts();
					System.out.println("Overdrafts: " + over);
					double fees = (over * c.getOverdraftFee());
					System.out.println("Overdraft fees: " + fees);
					c.withdraw(fees);
					System.out.println(c.getBalance());
				}
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
							c.withdraw(this.getBankingFee());
						}
					}
				}
				else
				{
					if (temp.getCheckingAccount() != null)
					{
						c = (CheckingAccount) temp.getCheckingAccount();
						double checkBalance = c.getBalance();
						if (checkBalance < this.getMinNoFeeCombinedBalance())
						{
							c.withdraw(this.getBankingFee());
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
