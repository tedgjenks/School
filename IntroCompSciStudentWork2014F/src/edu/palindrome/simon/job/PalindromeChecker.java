package edu.palindrome.simon.job;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome (String input) {
		// TODO Auto-generated method stub
		String LettersDigits= input;
		
		String ReversDigits="";
		
		for (int a=input.length()-1; a>=0; a--){
				
				char ch=input.charAt(a);
				
				if (Character.isLetterOrDigit(ch)){
				   String g = Character.toString(ch);
						
				if (!g.isEmpty()){
				ReversDigits = ReversDigits + ch;
				}
			
				
				}
					
			}
		ReversDigits.equals(LettersDigits); 
		return ReversDigits.equalsIgnoreCase(LettersDigits);	
	}

}

		
	