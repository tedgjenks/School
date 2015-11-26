package edu.banking.mathis.justin;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, AbstractCheckingAccount ca, AbstractSavingsAccount sa){
		super(name, ca, sa);
	}

	@Override
	public int compareTo(AbstractCustomer a) {
		String b = a.getName();
		String c = this.getName();
		return c.compareTo(b);
	}

	public String toString(){
		return this.getName();
	}

	public boolean equals(Customer b){
		return (b.toString()).equals(this.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
