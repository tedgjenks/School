package edu.banking.page.javin;
import edu.jenks.dist.banking.*;
public class Banking extends AbstractBanking{
	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		getCustomers().add(new Customer(arg0, arg1, arg2));
	}

	@Override
	public void performMaintenance(int arg0) {
		for(AbstractCustomer a : getCustomers()) {
			a.getCheckingAccount().payInterest(arg0);
			a.getCheckingAccount().setBalance(a.getCheckingAccount().getBalance() - (a.getCheckingAccount().getNumberOverdrafts() * a.getCheckingAccount().getNumberOverdrafts()));
			a.getCheckingAccount().setNumberOverdrafts(0);
			if(a.getSavingsAccount() != null) {
				a.getSavingsAccount().setNumTransactions(0);
			}
		}
	}

	@Override
	public boolean removeCustomer(String arg0) {
		for(int i = 0; i < getCustomers().size(); i++) {
			String name = getCustomers().get(i).getName();
			if(name.equals(arg0)) {
				getCustomers().remove(i);
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Banking newBankWhoDis = new Banking();
		Customer kevinHart = new Customer("Kevin Hart", new CheckingAccount(100, .0415), new SavingsAccount(100, .0415));
		kevinHart.getSavingsAccount().setMaxMonthlyTransactions(10);
		kevinHart.getSavingsAccount().setNumTransactions(0);
		kevinHart.getCheckingAccount().setOverdraftProtected(true);
		kevinHart.getCheckingAccount().setOverdraftMax(2000);
		kevinHart.getCheckingAccount().withdraw(375);
		kevinHart.getCheckingAccount().withdraw(100);
		System.out.println(kevinHart.getCheckingAccount().getNumberOverdrafts() + " " + kevinHart.getCheckingAccount().getBalance());
		newBankWhoDis.addCustomer("Kevin Hart", new CheckingAccount(), new SavingsAccount());
		newBankWhoDis.addCustomer("Chris Rock", new CheckingAccount(), new SavingsAccount());
		newBankWhoDis.addCustomer("Dave Chappelle", new CheckingAccount(), new SavingsAccount());
		newBankWhoDis.addCustomer("Aziz Ansari", new CheckingAccount(), new SavingsAccount());
	}
}
