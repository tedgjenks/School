package edu.palindrome.mathis.justin;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {

	@Override
	public boolean isPalindrome(String word) { 
		String wordwithout = word.replaceAll("\\P{Alnum}", "").toLowerCase();
		String reverse = new StringBuffer(word).reverse().toString();
		String reversewithout = reverse.replaceAll("\\P{Alnum}", "").toLowerCase();
		boolean result = reversewithout.equals(wordwithout);
		if (wordwithout.length() == 0){
			result = false;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word = "Hi!";
		String reverse = new StringBuffer(word).reverse().toString();
		String reversewithout = reverse.replaceAll("\\P{Alnum}", "").toLowerCase();
		System.out.print(reverse+"            "+reversewithout);
	}

}
