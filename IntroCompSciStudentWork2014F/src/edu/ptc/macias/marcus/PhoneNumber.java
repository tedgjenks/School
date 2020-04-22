package edu.ptc.macias.marcus;

import edu.jenks.dist.ptc.PhoneNumberable;

public class PhoneNumber implements PhoneNumberable {

	static String phone = "";

	public PhoneNumber(String phoneNumber) {
		setNumber(phoneNumber);
	}

	public static void main(String[] args) {
		PhoneNumber run = new PhoneNumber("1-800-111-2222");
		//System.out.println("12  13 456 -7 89    0");
		//System.out.println(run.toString());
		//System.out.println(run.getAreaCode());
		//System.out.println(run.getPrefix());
		
		
	}

	public boolean equals(Object obj) {
		PhoneNumberable cast = (PhoneNumberable)obj;
		if(cast.isValid() == false || this.isValid() == false) {
			return false;
		}
		if(this.getAreaCode().equals(cast.getAreaCode()) && this.getLineNumber().equals(cast.getLineNumber()) && this.getPrefix().equals(cast.getPrefix())) {
			return true;
		}
		return false;
	}

	public String toString() {
		phone = phone.replaceAll(" ", "");
		phone = phone.replaceAll("-", "");
		if(isValid() == false) {
			return "not valid";
		}
		if(isValid() == true && phone.length() == 11) {
			return getAreaCode() + "-" + getPrefix() + "-" + getLineNumber();
		}
		return getAreaCode() + "-" + getPrefix() + "-" + getLineNumber();
	}

	public String getAreaCode() {
		
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll(" ", "");
		
		if(isValid() == false) {
			return "unknown area code";
		}else if(isValid() == true && phone.length() == 11){
			return phone.substring(1,4);
		}
		else {
			return phone.substring(0, 3);
		}
	}

	public String getLineNumber() {
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll(" ", "");
		if(isValid() == false) {
			return "unknown line number";
		}else if(isValid() == true && phone.length() == 11) {
			return phone.substring(7, phone.length());
		}else {
			return phone.substring(6, phone.length());
		}
		
	}

	public String getPrefix() {
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll(" ", "");
		if(isValid() == false) {
			return "unknown prefix";
		}else if(isValid() == true && phone.length() == 11){
			return phone.substring(4, 7);
		}else {
			return phone.substring(3, 6);
		}
	}

	public boolean isValid() {
		boolean test = findSomethingOtherThanNumbers();
		if(test == false) {
			return false;
		}
		return true;
	}

	public boolean findSomethingOtherThanNumbers() {
		//int firstLength = phone.length();
		String thing = phone.replaceAll("-", "");
		thing = thing.replaceAll(" ", "");
		boolean contains = thing.matches("[0-9]+$");
		
		if(contains == true && thing.length() == 11 && thing.substring(0,1).equals("1")) {
			return true;
		}
		if(contains == true && thing.length() > 9 && thing.length() < 11) {
			return true;
		}
		return false;
		/*String copyPhone = phone;
		copyPhone = copyPhone.replaceAll("[a-zA-Z]", "");
		if(firstLength == 11) {
			if(copyPhone.substring(0,1) == "1" && copyPhone.length() == firstLength) {
				return true;
			}
		}
		if(copyPhone.length() != firstLength) {
			return false;
		}
		return true;*/
	}

	public void setNumber(String arg0) {
		phone = arg0;
	}

}
