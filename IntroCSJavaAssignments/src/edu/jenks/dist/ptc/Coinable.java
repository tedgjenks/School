/**
 * 
 */
package edu.jenks.dist.ptc;

/**
 * @author Ted Jenks
 *
 */
public interface Coinable {

	/**
	 * @return number of pennies
	 */
	int getNumPennies();
	
	/**
	 * @return number of nickels
	 */
	int getNumNickels();
	
	/**
	 * @return number of dimes
	 */
	int getNumDimes();
	
	/**
	 * @return number of quarters
	 */
	int getNumQuarters();
	
	/**
	 * @param numPennies
	 */
	void setNumPennies(int numPennies);
	
	/**
	 * @param numNickels
	 */
	void setNumNickels(int numNickels);
	
	/**
	 * @param numDimes
	 */
	void setNumDimes(int numDimes);
	
	/**
	 * @param numQuarters
	 */
	void setNumQuarters(int numQuarters);
	
	/**
	 * @return the dollar value of all coins in $d.c format where d is at least one digit and c is exactly two digits
	 */
	String getTotalDollars();
	
	/**
	 * @return the dollar value of quarters in $d.c format where d is at least one digit and c is exactly two digits
	 */
	String getDollarsFromQuarters();
	
	/**
	 * @return the dollar value of dimes in $d.c format where d is at least one digit and c is exactly two digits
	 */
	String getDollarsFromDimes();
	
	/**
	 * @return the dollar value of nickels in $d.c format where d is at least one digit and c is exactly two digits
	 */
	String getDollarsFromNickels();
	
	/**
	 * @return the dollar value of pennies in $d.c format where d is at least one digit and c is exactly two digits
	 */
	String getDollarsFromPennies();
}
