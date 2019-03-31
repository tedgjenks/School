package edu.palindrome.sweezy.kenneth;

import edu.jenks.dist.palindrome.*;

public class PalindromeChecker extends AbstractPalindromeChecker {
    public static void main(String[] args) {
        PalindromeChecker instance = new PalindromeChecker();
        System.out.println(instance.isPalindrome("%%"));
    }

    public boolean isPalindrome(String input) {
        String symbolsRemoved = correctSymbols(input);
        System.out.println(symbolsRemoved);
        int stringLength = symbolsRemoved.length();
        double midPointRound = stringLength / 2;
        int midPoint = (int)midPointRound;
        if(symbolsRemoved.equals("")) {
            return false;
        }
        for(int i = 0; i < midPoint; i++) {
            if(symbolsRemoved.charAt((midPoint + 1) - i) == symbolsRemoved.charAt((midPoint - 1) + i)){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public String correctSymbols(String input) {
        System.out.println(input.replaceAll("\\W", "").toUpperCase());
        return input.replaceAll("\\W", "").toUpperCase();
    }
}