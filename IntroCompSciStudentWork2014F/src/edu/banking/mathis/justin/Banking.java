package edu.banking.mathis.justin;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {


	public void performMaintenance(int days) {
		for (int i = 1; i <= getCustomers().size(); i++){
			Customer custo = (Customer)getCustomers().get(i-1);

			AbstractCheckingAccount ca = custo.getCheckingAccount();
			AbstractSavingsAccount sa = custo.getSavingsAccount();


			if (ca != null && sa != null){
				double overfee = (ca.getOverdraftFee()*ca.getNumberOverdrafts());
				double cabal = ca.getBalance();
				double sabal = sa.getBalance();
				ca.setNumberOverdrafts(0);
				sa.setNumTransactions(0);
				if (cabal >= overfee){
					ca.setBalance(cabal-overfee);
				}
				else if (cabal+sabal >= overfee){
					ca.setBalance(0);
					sa.setBalance(sabal-(overfee-cabal));
				}
				else {
					double deduction = (overfee-(sabal+cabal))+getBankingFee();
					ca.setBalance(cabal-deduction);
					sa.setBalance(0);
				}
				if (ca.getBalance()+sa.getBalance() <= getMinNoFeeCombinedBalance()){
					ca.setBalance(ca.getBalance()-getBankingFee());
					if (ca.getBalance() < 0 && sa.getBalance() > 0){
						if(sa.getBalance() > Math.abs(ca.getBalance())){
							sa.setBalance(sa.getBalance()-Math.abs(ca.getBalance()));
							ca.setBalance(0);
						}
						else if (sa.getBalance() < Math.abs(ca.getBalance()) && sa.getBalance() > 0){
							ca.setBalance(sa.getBalance()+ca.getBalance());
							sa.setBalance(0);
						}
						else{
							ca.setBalance(sa.getBalance()-ca.getBalance());
							sa.setBalance(0);
						}
						
					}
				}
			}
			else if (ca != null && sa == null){
				double overfee = (ca.getOverdraftFee()*ca.getNumberOverdrafts());
				double cabal = ca.getBalance();
				ca.setNumberOverdrafts(0);
				if (cabal >= overfee){
					ca.setBalance(cabal-overfee);
				}
				else{
					ca.setBalance((overfee-cabal)+getBankingFee());

				}
				if (ca.getBalance() <= getMinNoFeeCombinedBalance()){
					ca.setBalance(ca.getBalance()-getBankingFee());
				}
			}
			else if (ca == null && sa != null){
				sa.setNumTransactions(0);
				if (sa.getBalance() <= getMinNoFeeCombinedBalance()){
					sa.setBalance(sa.getBalance()-getBankingFee());
				}
			}

			if (sa != null && sa.getBalance() > 0){
				sa.payInterest(days);
			}
			if (ca != null && ca.getBalance() > 0){
				ca.payInterest(days);
			}
		}
	}

	public static void main(String[] args) {


	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount ca, AbstractSavingsAccount sa) {
		Customer a = new Customer(name, ca, sa);
		getCustomers().add(a);

	}

	@Override
	public boolean removeCustomer(String name) {
		boolean result = false;
		Customer a = new Customer(name);
		if (getCustomers().remove(a)){
			result = true;
		}
		return result;
	}

}
