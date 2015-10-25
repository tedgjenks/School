package edu.palindrome.shearer.richard;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) {
		int inputlength = input.length();
		char inputCharacter = 0;
		String inputString1 = "";
		String inputString2 = "";
		String inputString3 = "";
		String revString1 = "";
		String revString2 = "";
		String revString3 = "";
		for(int argu1 = 0; argu1 < inputlength; argu1++) 
		{
			inputCharacter = input.charAt(argu1);
			if(Character.isLetterOrDigit(inputCharacter)) 
			{
				inputString1 = Character.toString(inputCharacter);
				inputString2 = inputString2.concat(inputString1);
				inputString3 = inputString2.toLowerCase();
			}
		}
		for (int argu1 = inputlength -1; argu1 >= 0; argu1--) {
			inputCharacter = input.charAt(argu1);
				if (Character.isLetterOrDigit(inputCharacter)) {
			revString1 = Character.toString(inputCharacter);
			revString2 = revString2.concat(revString1);
			revString3 = revString2.toLowerCase();
				}
		}	
		System.out.println(inputString3);
		System.out.println(revString3);
		if(inputString3.length() == 0 || revString3.length() == 0)
			return false;
			if (inputString3.equals(revString3)){
			return true;
			}
			else;
		return false;
		}
	public static void main(String[] args) {
		PalindromeChecker cheese = new PalindromeChecker();
		boolean cake = cheese.isPalindrome("tacocat");
		if (cake == true)
			System.out.println("Its a palindrome!!!!!!!!!!");
		else
			System.out.println("ITS NOT A PLAINDROME, SCRUB!");
	}
}
