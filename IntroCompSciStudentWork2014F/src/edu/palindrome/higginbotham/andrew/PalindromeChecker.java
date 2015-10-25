package edu.palindrome.higginbotham.andrew;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) {
		int inputLength = input.length();
		char inputCharacter = 0;
		String inputString = "";
		String inputString2 = "";
		String inputString3 = "";
		String revString = "";
		String revString2 = "";
		String revString3 = "";
		
		for(int arg0 = 0; arg0 < inputLength; arg0++)
		{
			inputCharacter = input.charAt(arg0);
			if(Character.isLetterOrDigit(inputCharacter))
			{
				inputString = Character.toString(inputCharacter);
				inputString2 = inputString2.concat(inputString);
				inputString3 = inputString2.toLowerCase();
				
			}
			
		}
		for(int arg1 = inputLength -1; arg1 >= 0; arg1--){
			
			inputCharacter = input.charAt(arg1);
			if(Character.isLetterOrDigit(inputCharacter)) 
			{
			revString = Character.toString(inputCharacter);
			revString2 = revString2.concat(revString);
			revString3 = revString2.toLowerCase();
			}
			
		}
		System.out.println(inputString3);
		System.out.println(revString3);
		if(inputString3.length() == 0 || revString3.length() == 0)
		return false;
		if(inputString3.equals(revString3))
		return true;
		else 
		return false;
	}

	public static void main(String[] args) {
	
		PalindromeChecker pc = new PalindromeChecker();
		boolean arg0 = pc.isPalindrome("%%");
		if(arg0 == true)
		System.out.println("It is a palindrome");
		else
		System.out.println("It is not a palindrome");
		
		
	}

}
