package edu.palindrome.ball.daniel;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String input) 
	{
		char temp = 0;
		String tempString = "";
		String tempString2 = "";
		String tempString3 = "";
		String tempStringInput = "";
		String tempString2Input = "";
		String tempString3Input = "";
		int length = input.length();
		for (int i = 0; i < length; i++)
		{
			temp = input.charAt(i); 
			if (Character.isLetterOrDigit(temp))
			{
					tempStringInput = Character.toString(temp);
					tempString2Input = tempString2Input.concat(tempStringInput);
					tempString3Input = tempString2Input.toUpperCase();
			}
		}
		for (int i = 0; i < length; i++)
		{
			temp = input.charAt(i);
			if (Character.isLetterOrDigit(temp))
			{
					tempString = Character.toString(temp);
					tempString2 = tempString.concat(tempString2);
					tempString3 = tempString2.toUpperCase();
			}
		}
		if (!tempString3Input.isEmpty() && !tempString3.isEmpty() && tempString3Input.equals(tempString3))
				{
					return true;
				}
		else
				{
					return false;
				}
		}
	
	public static void main(String[] args) 
	{
		
	}

}
