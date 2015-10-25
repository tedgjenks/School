/**
 * 
 */
package edu.jenks.dist.palindrome;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractPalindromeChecker {

	/**
	 * <p>Test a string to see if it is a palindrome.</p>
	 * 
	 * Only alphanumeric characters are considered in the test.<br> 
	 * <p><b>precondition</b>: <code>arg</code> is not null</p>
	 * @param arg
	 * @return <code>true</code> if <code>arg</code> is a palindrome, otherwise <code>false</code>
	 */
	public abstract boolean isPalindrome(String arg);
}
