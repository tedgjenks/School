package edu.ptc.salter.bella;
import edu.jenks.dist.ptc.*;

public class PhoneNumber implements PhoneNumberable {
	String number = "";
	String areaCode = "";
	String prefix = "";
	String lineNumber = "";
	public static void main(String[] args) {
		PhoneNumber tester = new PhoneNumber("1-800-111 6222");
		System.out.println(tester.toString());
	}
	public PhoneNumber(String phoneNumber) {
		setNumber(phoneNumber);
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof PhoneNumberable)) {
			return false;
		}
		PhoneNumberable num = (PhoneNumberable)obj;
		if(num.getAreaCode().contentEquals(areaCode) && num.getPrefix().contentEquals(prefix) && num.getLineNumber().contentEquals(lineNumber)) {
			return true;
		}
		return false;
	}
	public String getAreaCode() {
		if(!isValid()) {
			return "unknown area code";
		}
		if(areaCode.length() == 3) {
			return areaCode;
		} 
		return "unknown area code";
	}
	public String getLineNumber() {
		if(!isValid()) {
			return "unknown line number";
		}
		if(lineNumber.length() == 4)
			return lineNumber;
		return "unknown line number";
	}
	public String getPrefix() {
		if(!isValid()) {
			return "unknown prefix";
		}
		if(prefix.length() == 3)
			return prefix;
		return "unknown prefix";
	}
	//this method checks to see if its valid number
	public boolean isValid() {
		for(int i = 0; i < number.length(); i++) {
			if(!isNum(number.substring(i, i+1)) && !(number.substring(i, i+1).equals(" ")  || number.substring(i, i + 1).equals("-"))) {
				return false;
			}
		}
		
		number = getJustNum(number);
		if(number.length() > 11 || number.length() < 10) {
			return false;
		} else if(number.length() == 11 && !(number.substring(0 , 1).contentEquals("1"))) {
			return false;
		}else if((areaCode.contentEquals("") || prefix.contentEquals("")) || lineNumber.contentEquals("")) {
			return false;
		} else {
			return true;
		}
	}
	//this method does stuff
	public void setNumber(String phoneNumber) {
		System.out.println(number);
		String num = "";
		num = phoneNumber;
		number = getJustNum(phoneNumber);
		if(number.length() == 11) {
			number = number.substring(1);
		}
		if(number.length() == 10) {
			areaCode = number.substring(0,3);
			prefix = number.substring(3,6);
			lineNumber = number.substring(6);
		}
		number = num;
		System.out.println(number);
		
	}
	public String toString() {
		if(!isValid()) {
			return "not valid";
		}
		String finalStr = "";
		finalStr = finalStr + areaCode + "-" + prefix + "-" + lineNumber;
		return finalStr;
	}
	public String getJustNum(String phoneNumber) {
		String num = "";
		for(int i = 0; i < phoneNumber.length(); i++) {
			if(isNum(phoneNumber.substring(i, i + 1))) {
				num = num + phoneNumber.substring(i, i + 1);
			}
			
		}
		return num;
	}
	public boolean isNum(String str) {
		return (str.contentEquals("0")|| str.contentEquals("1") || str.contentEquals("2") || str.contentEquals("3") || str.contentEquals("4") || str.contentEquals("5") || str.contentEquals("6") || str.contentEquals("7") || str.contentEquals("8") || str.contentEquals("9"));
	}
}
