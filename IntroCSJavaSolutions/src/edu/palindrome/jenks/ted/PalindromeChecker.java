/**
 * 
 */
package edu.palindrome.jenks.ted;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

/**
 * @author Ted Jenks
 *
 */
public class PalindromeChecker extends AbstractPalindromeChecker {
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.palindrome.AbstractPalindromeChecker#isPalindrome(java.lang.String)
	 */
	@Override
	public boolean isPalindrome(String arg) {
		int argLength = arg.length();
		StringBuilder alphaNumeric = new StringBuilder(argLength);
		for(int index = argLength - 1; index >= 0; index--) {
			char character = arg.charAt(index);
			if(Character.isLetterOrDigit(character))
				alphaNumeric.append(character);
		}
		String original = alphaNumeric.toString();
		String reversed = alphaNumeric.reverse().toString();
		return original.length() > 0 && original.equalsIgnoreCase(reversed);
	}

}
