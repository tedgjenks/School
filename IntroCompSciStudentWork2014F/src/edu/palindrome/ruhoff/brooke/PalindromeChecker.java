package edu.palindrome.ruhoff.brooke;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String word) {
		String drow="", o = "   ", nword = "";
		char space = o.charAt(1);
		boolean palindrome=false;
		for (int index = 0; index < word.length(); index++){
			char first = word.charAt(index);
			if (Character.isLetterOrDigit(first)&& first !=space){
				nword = nword + Character.toString(first);
			}
		}
		for (int index=0; index < word.length(); index++){
			char letter = word.charAt(word.length() - index+1);
			if (Character.isLetterOrDigit(letter)){
				if (letter != space){
					drow=drow + Character.toString(letter);
					if (index == word.length()-1)
						if (drow.equalsIgnoreCase(nword))
							palindrome = true;
				}
			}
			else {
				if (index == word.length()-1){
					palindrome = false;
				}
			}
		}
		System.out.println(palindrome);
		
	return palindrome;
	}
}