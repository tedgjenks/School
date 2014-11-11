package edu.jenks.dist.banking;

/**
 * @author JenksT
 *
 */
public abstract class AccountGen implements Account {
	private double balance;
	
	public AccountGen(double balance) {
		this.balance = balance;
	}
	
	public AccountGen() {
		this(0);
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
