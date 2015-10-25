package edu.palindrome.slimmer.ben;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker
extends AbstractPalindromeChecker{
	public static void main(String[] args){
		
	}

	public boolean isPalindrome(String arg0) {
		String reversestring= null;
		arg0=arg0.toLowerCase();
		String editarg0= null;
		for(int i=0;i<arg0.length();i++){
			if (Character.isLetter(arg0.charAt(i))==true){
				if (editarg0==null)
					editarg0=arg0.substring(i, i+1);
				else
					editarg0= editarg0+arg0.charAt(i);
			}
		}
		if (editarg0==null)
			return false;
		for(int i=editarg0.length()-1;i>=0;i--){
			if (reversestring==null)
				reversestring= editarg0.substring(i);
			else
				reversestring= reversestring+editarg0.charAt(i);
		}
		return editarg0.equals(reversestring);
	}
}
