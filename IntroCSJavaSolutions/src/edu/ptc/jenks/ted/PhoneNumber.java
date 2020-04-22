/**
 * 
 */
package edu.ptc.jenks.ted;

import edu.jenks.dist.ptc.PhoneNumberable;
import static java.lang.System.out;

import java.util.regex.*;

/**
 * @author Ted Jenks
 *
 */
public class PhoneNumber implements PhoneNumberable {
	
	private static final Pattern TEN_DIGITS = Pattern.compile("\\d{10}");
	
	public static void main(String[] args) {
		PhoneNumber pn = new PhoneNumber("1-1 1-6 6-60000");
		pn.validate();
		out.println(pn.valid);
	}

	private String number;
	boolean valid = false;
	
	/**
	 * Set the phone number to <code>phoneNumber</code> whether number is valid or not.
	 * 
	 * @param phoneNumber the phone number (not null, but not necessarily valid)
	 */
	public PhoneNumber(String phoneNumber) {
		number = phoneNumber;
		validate();
	}
	
	/**
	 * True if two valid phone numbers are the same, otherwise false
	 * 
	 * @param obj must be of type <code>PhoneNumberable</code>
	 * @return true if both PhoneNumberables are valid and numerically equivalent (excluding formatting), otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		PhoneNumberable arg = (PhoneNumberable)obj;
		return isValid() && arg.isValid() && toString().equals(arg.toString());
	}

	/**
	 * Area code - prefix - line number (e.g. 222-333-4444) OR <i>not valid</i> if not valid
	 */
	@Override
	public String toString() {
		if(valid) {
			StringBuilder sb = new StringBuilder(12).append(number);
			sb.insert(6, '-');
			sb.insert(3, '-');
			return sb.toString();
		} else
			return "not valid";
	}

	@Override
	public String getLineNumber() {
		return valid ? number.substring(6) : "unknown line number";
	}
	
	@Override
	public String getPrefix() {
		return valid ? number.substring(3, 6) : "unknown prefix";
	}
	
	@Override
	public String getAreaCode() {
		return valid ? number.substring(0, 3) : "unknown area code";
	}
	
	@Override
	public void setNumber(String number) {
		this.number = number;
		validate();
	}
	
	@Override
	public boolean isValid() {
		return valid;
	}
	
	private void validate() {
		number = number.replaceAll("[-\\s]", "");
		if(number.length() == 11 && number.charAt(0) == '1')
			number = number.substring(1);
		valid = TEN_DIGITS.matcher(number).matches();
	}
}
