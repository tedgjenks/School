package edu.palindrome.busbee.hunter;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) {
		int originalLength = input.length(), length;
		String alphaNumeric = "";
		String ciremunAhpla = "";
		for(length = originalLength - 1; length >= 0; length--){
			if(Character.isLetterOrDigit(input.charAt(length))){
				alphaNumeric += input.charAt(length);
			}
		}
		for(length = alphaNumeric.length() - 1; length >= 0; length--){
			ciremunAhpla += alphaNumeric.charAt(length);
		}
		if(alphaNumeric.length() == 0)
			return false;
		else
			return alphaNumeric.equalsIgnoreCase(ciremunAhpla);



	}
}