package edu.banking.slimmer.ben;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking
extends AbstractBanking{

	@Override
	public void performMaintenance(int days) {
		for(int i=0; i<this.getCustomers().size(); i++){
			Customer c= (Customer) getCustomers().get(i);
			int maxnum=0;
			if(c.getSavingsAccount()!=null){
				maxnum=c.getSavingsAccount().getMaxMonthlyTransactions();
				c.getSavingsAccount().setMaxMonthlyTransactions(1000);
			}
			if (c.getSavingsAccount()!=null)
				c.getSavingsAccount().setNumTransactions(0);
			if(c.getCheckingAccount()!=null){
				c=handleOverdrafts(c);
				c.getCheckingAccount().setNumberOverdrafts(0);
			}
			c= bankingFees(c);
			if(c.getCheckingAccount()!=null && c.getSavingsAccount()!=null)
				c=cleanUp(c);
			if(c.getCheckingAccount()!=null && c.getCheckingAccount().getBalance()>0)
				c.getCheckingAccount().payInterest(days);
			if(c.getSavingsAccount()!=null && c.getSavingsAccount().getBalance()>0)
				c.getSavingsAccount().payInterest(days);
			if(c.getSavingsAccount()!=null)
				c.getSavingsAccount().setNumTransactions(0);
			if(c.getSavingsAccount()!=null){
				c.getSavingsAccount().setMaxMonthlyTransactions(maxnum);
			}
			getCustomers().set(i, c);
		}
	}
	
	public Customer handleOverdrafts(Customer c){
		boolean org= c.getCheckingAccount().isOverdraftProtected();
		double orgmax= c.getCheckingAccount().getOverdraftMax();
		c.getCheckingAccount().setOverdraftProtected(true);
		c.getCheckingAccount().setOverdraftMax(10000000.0);
		c.getCheckingAccount().withdraw(c.getCheckingAccount().getOverdraftFee()*c.getCheckingAccount().getNumberOverdrafts());
		c.getCheckingAccount().setNumberOverdrafts(0);
		c.getCheckingAccount().setOverdraftProtected(org);
		c.getCheckingAccount().setOverdraftMax(orgmax);
		return c;
	}
	

	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		Customer newguy= new Customer(arg0,arg1,arg2);
		this.getCustomers().add(newguy);
		
	}

	@Override
	public boolean removeCustomer(String arg0) {
		for(int i=0;i<getCustomers().size();i++){
			if((getCustomers().get(i).toString()).equals(arg0)){
				getCustomers().remove(i);		
				return true;
			}
		}
		return false;
	}
	
	public Customer bankingFees(Customer c){
		double total=0.0;
		double rollingfee=getBankingFee();
		if (c.getCheckingAccount()!=null)
			total+= c.getCheckingAccount().getBalance();
		if (c.getSavingsAccount()!=null)
			total+=c.getSavingsAccount().getBalance();
		
		if (total>=this.getMinNoFeeCombinedBalance())
			return c;
		else{
			if(c.getCheckingAccount()!=null){
				boolean org= c.getCheckingAccount().isOverdraftProtected();
				double orgmax= c.getCheckingAccount().getOverdraftMax();
				c.getCheckingAccount().setOverdraftProtected(true);
				c.getCheckingAccount().setOverdraftMax(10000000.0);
				c.getCheckingAccount().withdraw(this.getBankingFee());
				c.getCheckingAccount().setNumberOverdrafts(0);
				c.getCheckingAccount().setOverdraftProtected(org);
				c.getCheckingAccount().setOverdraftMax(orgmax);
				return c;
			}
			else
				c.getSavingsAccount().setBalance(c.getSavingsAccount().getBalance()-this.getBankingFee());
			return c;
		}
	}
	
	public Customer cleanUp(Customer c){
		if(c.getCheckingAccount().getBalance()<0 && c.getSavingsAccount().getBalance()>0){
			if(c.getCheckingAccount().getBalance()*-1<c.getSavingsAccount().getBalance()){
				c.getSavingsAccount().transfer(c.getCheckingAccount(), c.getCheckingAccount().getBalance()*-1);
				c.getSavingsAccount().setNumTransactions(0);
				return c;
			}
			else{
				c.getSavingsAccount().transfer(c.getCheckingAccount(), c.getSavingsAccount().getBalance());
				c.getSavingsAccount().setNumTransactions(0);
				return c;
			}
		}
		return c;
	}
}
