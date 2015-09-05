package edu.phone.slimmer.ben;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber
extends AbstractRandomPhoneNumber{
	
	public static void main(String[] args) {
	}
	
	public int firstDigits() {
		return (int) (Math.random()*8);
	}
	public int middleDigits(){
		boolean comp=false;
		int digits=0;
		while (comp==false) {
			digits= (int) (Math.random()*743);
			if (digits>=100)
				comp=true;
		}
		return digits;
	}
	public int lastDigits(){
		boolean comp=false;
		int digits=0;
		while(comp==false) {
			digits=(int) (Math.random()*10000);
			if (digits>=1000)
				comp=true;
		}
		return digits;
	}
	@Override
	public String generateRandomPhoneNumber() {
		String phonenumber= firstDigits()+""+firstDigits()+""+firstDigits()+"-"+middleDigits()+"-"+lastDigits();
		return phonenumber;
	}

}
