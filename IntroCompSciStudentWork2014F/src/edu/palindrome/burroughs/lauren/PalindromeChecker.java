package edu.palindrome.burroughs.lauren;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String phrase) {
		String rebuiltphrase = "";
		
		String  reversedphrase = "";
		int length = phrase.length();  
		for(int index = length-1; index >= 0 ;index--){
			char letter = phrase.charAt(index);
			if(Character.isLetterOrDigit(letter)){
				rebuiltphrase = letter + rebuiltphrase;
				reversedphrase = reversedphrase + letter;
			}
		}
		
		boolean compare = rebuiltphrase.equalsIgnoreCase(reversedphrase);
		return compare;
		
	}
}
