package edu.banking.mariscal.juan;



import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking  {

	@Override
	public void performMaintenance(int days) {
		// TODO Auto-generated method stub
		for(int n = 1; n <= getCustomers().size(); n++){

			Customer cus = (Customer) getCustomers().get(n-1);
			if(cus !=null){
				CheckingAccount ca = (CheckingAccount)cus.getCheckingAccount();
				SavingsAccount sa = (SavingsAccount) cus.getSavingsAccount();
				double sb =0, cb = 0, fee = 0;
				if(ca != null && sa !=null ){
					sb = sa.getBalance();
					cb = ca.getBalance();
					double ccb = cb + sb;

					fee = ca.getOverdraftFee()* ca.getNumberOverdrafts();

					if(ccb < getMinNoFeeCombinedBalance()){
						fee = fee + getBankingFee();
					}
					if(ccb < fee){
						ca.setBalance(ccb - fee);
						sa.setBalance(0);

					}
					else if (cb < fee && sb > fee){
						double amount = fee - cb;
						ca.setBalance(0);
						sa.setBalance(sb - amount);
					}
					else  {

						ca.setBalance( cb - fee);


					}
					sa.setNumTransactions(0);
					sa.payInterest(days);
					ca.payInterest(days);
					ca.setNumberOverdrafts(0);}
				else if(sa!=null  ){
					sb = sa.getBalance();
					if(sb < getMinNoFeeCombinedBalance()){
						fee = getBankingFee();
						sb -= fee;
					}
					sa.setBalance(sb);
					sa.setNumTransactions(0);
					sa.payInterest(days);
				}
				else if(ca != null ){
					cb = ca.getBalance();
					fee = ca.getOverdraftFee() * ca.getNumberOverdrafts();
					if(cb < getMinNoFeeCombinedBalance()){
						fee = fee + getBankingFee();
					}
					cb -= fee;
					ca.setBalance(cb);
					ca.payInterest(days);
					ca.setNumberOverdrafts(0);}
			}}}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount ca, AbstractSavingsAccount sa) {
		// TODO Auto-generated method stub
		Customer cus = new Customer(name, ca, sa);
		getCustomers().add(cus);
	}

	@Override
	public boolean removeCustomer(String name) {
		// TODO Auto-generated method stub
		boolean re = false;
		for (int n = 1; n < getCustomers().size(); n++){
			Customer ha = (Customer)getCustomers().get(n-1);
			if(ha.getName().equals(name)){

				getCustomers().remove(n);
				re = true;
			}

		}
		return re;
	}

}
