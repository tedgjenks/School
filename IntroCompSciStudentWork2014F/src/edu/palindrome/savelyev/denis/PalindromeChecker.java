package edu.palindrome.savelyev.denis;

import edu.jenks.dist.palindrome.*;

public class PalindromeChecker extends AbstractPalindromeChecker{
    public static void main(String[] args){
        PalindromeChecker instance = new PalindromeChecker();
        boolean act = instance.isPalindrome("racecar");
        String exp = "hello";
        System.out.println();
    }
    public boolean isPalindrome(String input){
        String filteredWord = filterSymbols(input);
        int i1 = 0;
        int i2 = filteredWord.length() - 1;
        if(i2 != -1){
            while(i1 < i2){
                if(filteredWord.charAt(i1) != filteredWord.charAt(i2)){
                    System.out.println(filteredWord + " is not a palindrome");
                    return false;
                }
                i1++;
                i2--;
            }
        } else {
            System.out.println(filteredWord + " is not a palindrome");
            return false;
        }
        System.out.println(filteredWord + " is a palindrome");
        return true;
    }
    public String filterSymbols(String input){
        String replaceText = input.replaceAll("\\W", "");
        String finalWord = replaceText.toLowerCase();
        return finalWord;
    }
}
