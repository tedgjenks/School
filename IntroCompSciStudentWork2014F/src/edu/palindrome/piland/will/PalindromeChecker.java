package edu.palindrome.piland.will;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String word) {
		// TODO Auto-generated method stub
		int loopCount;
		String drow = "";
		String newWord = "";
		for (loopCount = 0; loopCount <= (word.length() - 1); loopCount++)
			if (Character.isLetterOrDigit(word.charAt(loopCount))){
				newWord += word.charAt(loopCount); 
			}

		for (loopCount = 0; loopCount <= (newWord.length() - 1); loopCount++)
			drow = drow + newWord.charAt((newWord.length()-1) - loopCount);
	
		if (newWord.equalsIgnoreCase(drow))
			return true;
		else 
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int loopCount;
		String word = "Williw";
		String drow = "";
		String newWord = "";
		for (loopCount = 0; loopCount <= (word.length() - 1); loopCount++)
			if (Character.isLetterOrDigit(word.charAt(loopCount))){
				newWord += word.charAt(loopCount); 
			}

		for (loopCount = 0; loopCount <= (newWord.length() - 1); loopCount++)
			drow = drow + newWord.charAt((newWord.length()-1) - loopCount);
	
		if (newWord.equalsIgnoreCase(drow))
			System.out.println("true");
		else
			System.out.println("false");
	}

}
