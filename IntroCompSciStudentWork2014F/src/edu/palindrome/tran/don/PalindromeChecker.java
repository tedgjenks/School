package edu.palindrome.tran.don;

import edu.jenks.dist.palindrome.*;

public class PalindromeChecker extends AbstractPalindromeChecker
{
    public static void main (String[] args) {
        PalindromeChecker pc = new PalindromeChecker(); 
        boolean act = pc.isPalindrome("%%");
        System.out.println(act);
    }
    public boolean isPalindrome(String arg) {
       arg = arg.replaceAll("[^A-Za-z_0-9]", "");
       //System.out.println(yyes + "what");
       if(arg.length() == 0) {
           return false;
       }
       System.out.println(arg);
       String firstHalf = "";
       String firstHalfReal = "";
       String secondHalfReal = "";
       if(arg.length() % 2 == 0) {
          firstHalf = arg.substring(0, arg.length() / 2);
          firstHalfReal = firstHalf.toLowerCase();
       } else {
          firstHalf = arg.substring(0, (arg.length() / 2) + 1);
          firstHalfReal = firstHalf.toLowerCase();
       }
       //System.out.println(firstHalf);
       String secondHalf = "";
       int halfLength = arg.length() / 2;
       int length = arg.length();
       for(int i = length - 1; i > halfLength - 1; i--) {
           secondHalf += arg.charAt(i);
       }
       secondHalfReal = secondHalf.toLowerCase();
       //System.out.println(secondHalf);
       if(secondHalfReal.equals(firstHalfReal)) {
          return true;
       } else {
           return false;
       }

    }
}
