package edu.palindrome.wicker.marshall;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker{
	
	@Override
	public boolean isPalindrome(String input) {
		String lowerCaseInput = input.toLowerCase();
		StringBuilder alphaNumeric = new StringBuilder();
		char currentChar;
		
		for (int index = 0; index < lowerCaseInput.length(); index++){
			currentChar = lowerCaseInput.charAt(index);
			if (Character.isLetterOrDigit(currentChar)){
				System.out.println(currentChar);
				alphaNumeric.append(currentChar);
			}
		}
		
		if (alphaNumeric.length() > 0){
			StringBuilder reverseString = new StringBuilder(alphaNumeric.toString()).reverse();
			return alphaNumeric.toString().equals(reverseString.toString());
		}
		else{
			return false;
		}
	}

}
