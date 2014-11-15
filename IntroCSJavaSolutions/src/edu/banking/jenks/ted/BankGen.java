/**
 * 
 */
package edu.banking.jenks.ted;

import edu.jenks.dist.banking.Bank;
import edu.jenks.dist.banking.Customer;

import java.util.*;

/**
 * @author JenksT
 *
 */
public class BankGen implements Bank {

	private Map<String, Customer> customers = new HashMap<String, Customer>();
	
	/**
	 * 
	 */
	public BankGen() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.banking.Bank#addCustomer(edu.jenks.dist.banking.Customer)
	 */
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub

	}
}
