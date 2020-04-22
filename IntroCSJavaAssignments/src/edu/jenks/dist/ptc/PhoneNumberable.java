/**
 * 
 */
package edu.jenks.dist.ptc;

/**
 * For phone number AAA-BBB-CCCC, AAA is the area code, BBB is the prefix, and CCCC is the line number.
 * 
 * @author Ted Jenks
 */
public interface PhoneNumberable {
	/**
	 * @return the 4-digit line number or <i>unknown line number</i> if not valid
	 */
	public String getLineNumber();
	
	/**
	 * @return the 3-digit prefix or <i>unknown prefix</i> if not valid
	 */
	public String getPrefix();
	
	/**
	 * @return the 3-digit area code or <i>unknown area code</i> if not valid
	 */
	public String getAreaCode();
	
	/**
	 * Update the current phone number to <code>number</code> whether number is valid or not
	 * 
	 * @param number the new phone number
	 */
	public void setNumber(String number);
	
	/**
	 * The phone number must have a 3-digit area code, a 3-digit prefix, and a 4-digit line number.<br>
	 * An 11-digit number is legal if the first digit is 1 (the following 3 digits would be the area code).<br>
	 * Spaces and hyphens many be inserted anywhere for readability.
	 * 
	 * @return true if valid, otherwise false
	 */
	public boolean isValid();

}
