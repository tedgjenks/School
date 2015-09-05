/**
 * 
 */
package edu.jenks.dist.phone;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractRandomPhoneNumber {

	/**
	 * <p>Generates a random phone number in the form ###-###-####.</p>
	 * 
	 * <p>The area code must not contain an 8 or a 9.</p>
	 * <p>The exchange must be less than or equal to 742.</p>
	 * 
	 * @return a random phone number
	 */
	public abstract String generateRandomPhoneNumber();
}
