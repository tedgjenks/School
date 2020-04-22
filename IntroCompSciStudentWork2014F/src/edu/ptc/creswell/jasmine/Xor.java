package edu.ptc.creswell.jasmine;
import edu.jenks.dist.ptc.*;
public class Xor implements Xorable{
	
	public static void main(String[] args) {
		Xor test = new Xor(); 
		System.out.println(test.xorBinaryString("11"));
	}
	
	public boolean xorBinaryString(String word) {
		
	// helper method that returns the output of an exclusive or between each character	

			
	//loop that	checks to see if the first 2 characters evaluate to true or false	
			while (word.length()>=2)
	// checks to see if the first and second character are the same
				if (word.charAt(0)== word.charAt(1) ) {
	//if they are the same then it replaces the first 2 characters with "0"
					word= "0" + word.substring(2);
				} else {
	//if they are not the same then it replaces the first 2 characters with "1"
					word= "1" + word.substring(2);
			}
			if (word.charAt(0)=='0') {
				return false;
				
			} else {
				return true;
			}
			
		}
	
}
