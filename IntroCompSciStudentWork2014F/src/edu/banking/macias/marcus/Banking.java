package edu.banking.macias.marcus;


import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking{
	
	/*private String name;
	private AbstractCheckingAccount chckingAccount;
	private AbstractSavingsAccount savingsAccount;
	*/
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		getCustomers().add(new Customer(name, checkingAccount, savingsAccount));

	}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer cust: getCustomers()) {
			boolean isSavings = cust.getSavingsAccount() != null;
			int numOverdrafts = cust.getCheckingAccount().getNumberOverdrafts();
			if(isSavings) {
				
			}
		}
		
	}

	public static void main(String[] args) {
		Banking run = new Banking();
		for(AbstractCustomer a : run.getCustomers() ) {
			System.out.print(a.getName() + " ");
		}
		
		run.addCustomer("Marcus",new CheckingAccount(),new SavingsAccount());
		run.addCustomer("Don",new CheckingAccount(),new SavingsAccount());
		run.addCustomer("Maddux",new CheckingAccount(),new SavingsAccount());
		run.addCustomer("Griffin",new CheckingAccount(),new SavingsAccount());
		run.addCustomer("Alex",new CheckingAccount(),new SavingsAccount());
		run.addCustomer("Ryan",new CheckingAccount(),new SavingsAccount());
		for(AbstractCustomer a : run.getCustomers() ) {
			System.out.print(a.getName() + " ");
		}
		System.out.println(" ");
		System.out.println(run.removeCustomer("Maddux"));
		//System.out.println(" ");
		for(AbstractCustomer a : run.getCustomers() ) {
			System.out.print(a.getName() + " ");
		}
		System.out.println(run.removeCustomer("Maddux"));
	}
	public boolean removeCustomer(String name) {
		//List<AbstractCustomer> thing  = getCustomers();
		boolean containsName = false;
		
		int i = 0;
		for(AbstractCustomer a: getCustomers()) {
			
			if((a.getName()).equals(name)) {
				
				containsName = true;
				break;
			}
			i++;
		}
		if(containsName) {
			getCustomers().remove(i);
			return true;
		}
		return false;
	}

}
