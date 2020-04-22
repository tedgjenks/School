package edu.ptc.rhodes.maddux;

import edu.jenks.dist.ptc.*;

public class PhoneNumber implements PhoneNumberable {
	
	//member variables
	String phoneNumber = "invalid number";
	String areaCode = "unknown area code";
	String prefix = "unknown prefix";
	String lineNumber = "unknown line number";
	boolean isValid = true;

	//constructor
	public PhoneNumber(String phoneNumber) {
		setNumber(phoneNumber);
	}

	public static void main(String[] args) {
		
	}

	//checks to see if argument and calling argument are the same
	public boolean equals(Object obj) {
		PhoneNumberable compare = (PhoneNumberable)obj;
		if(isValid() == false || compare.isValid() == false) {
			return false;
		}
		String newPhone = getAreaCode() + getPrefix() + getLineNumber();
		String otherPhone = compare.getAreaCode() + compare.getPrefix() + compare.getLineNumber();
		return newPhone.equals(otherPhone);
	}

	//formats phone number into areacode-prefix-linenumber
	public String toString() {
		if(isValid()) {
			return getAreaCode() + '-' + getPrefix() + '-' + getLineNumber();
		}else {
			return "not valid";
		}
	}

	//returns area code from member variable
	public String getAreaCode() {
		return areaCode;
	}

	//returns line number from member variable
	public String getLineNumber() {
		return lineNumber;
	}

	//returns prefix from member variable
	public String getPrefix() {
		return prefix;
	}

	//checks to see if the phone number only contains digits
	public boolean isValid() {
		if(isValid == false) {
			return false;
		}
		return !(areaCode.equals("unknown area code"));
	}

	//checks to see if phoneNumber is an empty string first, then removes all hyphens and spaces first if phoneNumber is valid. 
	//then makes substrings of all parts of the number (area code, prefix, line number) respectively. Or if they aren't valid 
	//returns not valid or something close to that with specific part of phone that isn't valid.
	public void setNumber(String number) {
		if (number.equals("")) {
			isValid = false;
		} else {
			isValid = true;
		}
		phoneNumber = number.replace("-", "").replace(" ", "");
		boolean containsLetters = !phoneNumber.matches("[0-9]+$");
		if (!containsLetters) {
			if (phoneNumber.length() == 10 || (phoneNumber.length() == 11 && phoneNumber.charAt(0) == '1')) {
				lineNumber = phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length());
				prefix = phoneNumber.substring(phoneNumber.length() - 7, phoneNumber.length() - 4);
				areaCode = phoneNumber.substring(phoneNumber.length() - 10, phoneNumber.length() - 7);
			} else {
				phoneNumber = "invalid number";
				areaCode = "unknown area code";
				prefix = "unknown prefix";
				lineNumber = "unknown line number";
			}
		} else {
			phoneNumber = "invalid number";
			areaCode = "unknown area code";
			prefix = "unknown prefix";
			lineNumber = "unknown line number";
		}
	}
}