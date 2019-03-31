package edu.palindrome.whitt.rose;

import edu.jenks.dist.palindrome.*;
/**
 * Write a description of class PalindromeChecker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PalindromeChecker extends AbstractPalindromeChecker
{
    public static void main(String[] args) {
        //object that allows non-static to be called
        PalindromeChecker pc = new PalindromeChecker();
        
        //original string
        String paliTest = "race car*(&*";
        //print out original string
        System.out.println("Original: " + paliTest);
        
        //length of string
        System.out.println("Original string length: " + paliTest.length());
        
        System.out.println(pc.alphabet(paliTest));
        
        System.out.println(pc.isPalindrome(paliTest));
        System.out.println(pc.finalTest(paliTest));
    }
    public boolean isPalindrome(String arg) {
        //String original = arg;
        //String flip = "";
        //if (original.contains(" ")) {
        //String thing = arg.replace(" ", "");
        //}
        
        String low = arg.toLowerCase();
        String alph = "";
        int len = low.length();
        for(int i = len - 1; i >= 0; i--) {
            if(Character.isLetterOrDigit(low.charAt(i))) {
                alph += low.charAt(i);
            }
        }
        
        String lowOrig = arg.toLowerCase();
        String fixOrig = "";
        int fixOrigLen = arg.length();
        for(int i = 0; i <= fixOrigLen - 1; i++) {
            if(Character.isLetterOrDigit(low.charAt(i))) {
                fixOrig += lowOrig.charAt(i);
            }
        }
        
        if(alph == "") {
            return false;
        } else {
            if (alph.equals(fixOrig)) {
                return true;
            } else {
                return false;
            }
        }
        //String low = arg.toLowerCase();
        //String originalLength = 
        //return true;
        
        //int length = original.length();
        //for(int i = length - 1; i>= 0; i--) {
        //    flip = flip + arg.charAt(i);
        //}
        //if (original == flip) {
            //return true;
        //} else {
            //return false;
        //}
        
        //isAlphabetic
    }
    public String alphabet(String arg) {
        String alph = "";
        
        int len = arg.length();
        for(int i = len - 1; i >= 0; i--) {
            if(Character.isLetterOrDigit(arg.charAt(i))) {
                alph += arg.charAt(i);
            }
        }
        return alph;
    }
    public String finalTest(String arg) {
        String low = arg.toLowerCase();
        String alph = "";
        int len = low.length();
        for(int i = len - 1; i >= 0; i--) {
            if(Character.isLetterOrDigit(low.charAt(i))) {
                alph += low.charAt(i);
            }
        }
        String thingy = "Original: " + arg + " Lower: " + low + " Final: " + alph;
        return thingy;
    }
}
