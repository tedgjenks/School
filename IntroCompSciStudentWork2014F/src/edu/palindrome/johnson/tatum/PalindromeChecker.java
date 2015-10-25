package edu.palindrome.johnson.tatum;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String inputWord) {
		int wordLength = inputWord.length() , length;
		String alphaNumeric1 = "";
		String alphaNumeric2 = "";
		for (length = wordLength - 1; length >= 0 ; length--){
			if (Character.isLetterOrDigit(inputWord.charAt(length)))
			alphaNumeric1 += inputWord.charAt(length);
		}
		for (length = alphaNumeric1.length() - 1; length >= 0; length--){
			alphaNumeric2 +=alphaNumeric1.charAt(length);
		}
		if (alphaNumeric1.length() == 0)
			return false;
		else			
			return alphaNumeric1.equalsIgnoreCase(alphaNumeric2);
	}
}

