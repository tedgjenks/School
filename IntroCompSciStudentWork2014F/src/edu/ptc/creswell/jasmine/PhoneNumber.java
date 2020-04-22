package edu.ptc.creswell.jasmine;
import edu.jenks.dist.ptc.*;

public class PhoneNumber implements PhoneNumberable {
	
	String pn;
	public static void main( String [] args) {
		PhoneNumber test = new PhoneNumber("     4-32 0-----49   3---750");
		//System.out.println(test.isValid());
		//System.out.println(test.getAreaCode());
		//System.out.println(test.getPrefix());
		//System.out.println(test.getLineNumber());
		System.out.println("test" + test.equals(new PhoneNumber("     4-32 0-----49   3---750")));
		
		
		
	}
	public PhoneNumber(String phoneNumber ) {
		pn=phoneNumber; 
		
	}
	public boolean equals(Object passedIn) {
		PhoneNumberable number = (PhoneNumberable)passedIn;
		
		if (isValid()) {
			if (number.getAreaCode().equals(getAreaCode()) && number.getPrefix().equals(getPrefix()) && number.getLineNumber().equals(getLineNumber())) {
				return true;
			}
			
		} 
		return false;
	}
	
	public String toString() {
		if (isValid()) {
			return getAreaCode() + "-" + getPrefix()+ "-" + getLineNumber();
		} else {
			return "not valid";
		}
		
	}
	// returns the area code
	public String getAreaCode() {
		if (isValid() && pn.length()==10) {
			return pn.substring(0,3);
		}
		if (isValid() && pn.length()==11) {
			return pn.substring(1,4);
		} else {
			return "unknown area code";
		}
		
	}

	@Override
	//returns the line number
	public String getLineNumber() {
		
		if (isValid() && pn.length()==10) {
			return pn.substring(6,10);
		}
		if (isValid() && pn.length()==11) {
			return pn.substring(7,11);
		} else {
			return "unknown line number";
		}
	}

	
	public String getPrefix() {
		
	
		if (isValid() && pn.length()==10) {
			return pn.substring(3,6);
		}
		if (isValid() && pn.length()==11) {
			return pn.substring(4,7);
		} else {
			return "unknown prefix";
		}
	}

	// returns if it is valid
	public boolean isValid() {
		
		pn=pn.replaceAll("-", "");
		pn=pn.replaceAll(" ", ""); 
		
		for (int i= 0; i<pn.length(); i++ )	{
			if (Character.isDigit((pn.charAt(i))) || pn.charAt(i)=='-' || pn.charAt(i)== ' ' ) {
				System.out.println(i);
				continue;
			} else {
				return false; 
			}
		}
		pn.replaceAll("-", "");
		pn.replaceAll(" ", ""); 
		
		if (pn.length()==10 || (pn.length()==11 && pn.charAt(0)=='1') ) {
			return true; 
		} else {
			return false;
		}
		
	}

	@Override
	public void setNumber(String arg0) {
		pn=arg0;
		
		
	}

}
