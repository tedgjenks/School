package edu.palindrome.hines.jonathan;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) 
	{
		int inputLength = input.length();
		char inputCar = 0;
		String iiinputString1 = "";
		String iiinputString2 = "";
		String iiinputString3 = "";
		String revvvString1 = "";
		String revvvString2 = "";
		String revvvString3 = "";
		
		
		for (int j = 0; j < inputLength; j++)
		{
			inputCar = input.charAt(j);
			if (Character.isLetterOrDigit(inputCar))
			{
				iiinputString1 = Character.toString(inputCar);
				iiinputString2 = iiinputString2.concat(iiinputString1);
				iiinputString3 = iiinputString2.toUpperCase();
			}
			
		}
		if (iiinputString3.length() == 0)
		{
			System.out.println("False");
			return false;
		}
		for (int j = 0; j < inputLength; j++)
		{
			inputCar = input.charAt(j);
			if (Character.isLetterOrDigit(inputCar))
			{
				revvvString1 = Character.toString(inputCar);
				revvvString2 = revvvString1.concat(revvvString2);
				revvvString3 = revvvString2.toUpperCase();
		
			}	
		}
System.out.println(iiinputString3);
System.out.println(revvvString3);
	if (iiinputString3.equals(revvvString3))
	{
		System.out.println("True");	
		return true;	
	}
	else
		System.out.println("False");
		return false;
	
	}
	public static void main(String[] args) 
	{
		PalindromeChecker pc = new PalindromeChecker();
		pc.isPalindrome(""); 
	}	
	
	}
