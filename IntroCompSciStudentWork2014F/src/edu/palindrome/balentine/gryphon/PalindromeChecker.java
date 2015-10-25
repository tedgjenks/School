package edu.palindrome.balentine.gryphon;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) {
		//
		//isLetterOrDigit(char character) is a boolean that determines if character is a letter or digit
		//returns true if so, otherwise false
		//
		String inputLetterDigits="";
		String reverseLetterDigits="";
		for(int j=input.length()-1; j>=0; j--){
			char ch=input.charAt(j);
			if(Character.isLetterOrDigit(ch)){
				String i = Character.toString(ch);
				if (!i.isEmpty()){
					inputLetterDigits=ch+inputLetterDigits;
					reverseLetterDigits=reverseLetterDigits+ch;
					//System.out.println("character added");
				}
			}
		}
		System.out.println("Input: " + input);
		System.out.println("");
		System.out.print("Reverse (alphanumeric): ");
		System.out.println(reverseLetterDigits);
		System.out.println("");
		System.out.print("Is \"" + input + "\" a palindrome? ");
		if(!inputLetterDigits.isEmpty() && inputLetterDigits.equalsIgnoreCase(reverseLetterDigits))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		PalindromeChecker pc=new PalindromeChecker();
		String input="%"; //-----------------------------------------------------
		boolean output=pc.isPalindrome(input);
		System.out.print(output);

	}

}
