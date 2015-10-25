package edu.palindrome.mariscal.juan;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String phrase) {
		// TODO Auto-generated method stub
		String pow = phrase.replaceAll("\\P{Alnum}", "").toLowerCase();
		String reverse = new StringBuffer(phrase).reverse().toString();
		String row = reverse.replaceAll("\\P{Alnum}", "").toLowerCase();
		boolean pal = pow.equalsIgnoreCase(row);
		if (pow.length() == 0 ){
			pal = false; }
		System.out.println(pal);
		return pal;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
