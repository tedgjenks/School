package edu.palindrome.ramsey.will;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String word) {
		// TODO Auto-generated method stub
		int wordLength = word.length();
		String rebuildWord = "";
		String reverseWord = "";
		boolean checkChar;
		int reverseWordLength = reverseWord.length();
		int count = 0;
		String wordChar = null;
		for (;count < wordLength; count++){
			wordChar = String.valueOf(word.charAt(count));
			checkChar = Character.isLetterOrDigit(word.charAt(count));
			if (checkChar)
				rebuildWord = rebuildWord.concat(wordChar);
		}
		int rebuildWordLength = rebuildWord.length();
			
		for ( count = 1; reverseWordLength < rebuildWordLength; count++){
			 wordChar = String.valueOf(rebuildWord.charAt(rebuildWord.length() - count));
					 reverseWord = reverseWord.concat(wordChar);
					  reverseWordLength = reverseWord.length();
		 }
		boolean returnPalindrome = rebuildWord.equalsIgnoreCase(reverseWord);
		System.out.println(returnPalindrome);
		System.out.println(reverseWord);
		return returnPalindrome;
	}

}
