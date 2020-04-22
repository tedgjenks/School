package edu.ptc.burroughs.trent;
import edu.jenks.dist.ptc.*;

public class PhoneNumber implements PhoneNumberable{

	//this store the phone number to be used everywhere
	private String pNumber;
	
	public static void main(String[] args) {
		PhoneNumber test = new PhoneNumber("18----5   41 626-----13     4");
		PhoneNumber lit = new PhoneNumber("18541626134");
		System.out.println(test.equals(lit));
	}
	
	public PhoneNumber(String phoneNumber) {
		pNumber = phoneNumber.replaceAll("-", "").replaceAll(" ", "");
	}
	
	//compares my pNumber to the passed in object obj and return true if they are equal
	public boolean equals(Object obj) {
		PhoneNumberable str = ((PhoneNumberable) obj); 
		String number = str.getAreaCode() + str.getPrefix() + str.getLineNumber();
		String compareTo = pNumber;
		if(compareTo.length() == 11) {
			compareTo = compareTo.substring(1,11);
		}
		if(number.replaceAll("-", "").replaceAll(" ", "").equals(compareTo.replaceAll("-", "").replaceAll(" ", ""))) {
			return true;
		}
		return false;
	}

	//returns a string of the area code
	public String getAreaCode() {
		if(isValid()) {
			if(pNumber.length() == 11) {
				return pNumber.substring(1, 4);
			} else {
				return pNumber.substring(0, 3);
			}
		}
		return "unknown area code";
	}

	//return a string of the line number
	public String getLineNumber() {
		if(isValid()) {
			if(pNumber.length() == 11) {
				return pNumber.substring(7, 11);
			} else {
				return pNumber.substring(6, 10);
			}
		}
		return "unknown line number";
	}

	//returns a string of the number prefix
	public String getPrefix() {
		if(isValid()) {
			if(pNumber.length() == 11) {
				return pNumber.substring(4, 7);
			} else {
				return pNumber.substring(3, 6);
			}
		}
		return "unknown prefix";
	}

	//checks to see if its a valid phone number
	public boolean isValid() {
		pNumber.replaceAll("-", "").replaceAll(" ", "");
		if(pNumber.matches("[0-9]+$")) {
			if(pNumber.length() == 11 && pNumber.charAt(0) == '1') {
				return true;
			} else if(pNumber.length() == 10) {
				return true;
			}
		}
		return false;
	}

	//sets pNumber to arg0
	public void setNumber(String arg0) {
		pNumber = arg0.replaceAll("-", "").replaceAll(" ", "");
	}
	
	//prints pNumber in ###-###-#### form
	public String toString() {
		if(isValid())
			return getAreaCode() + "-" + getPrefix() + "-" + getLineNumber();
		return "not valid";
	}

}
