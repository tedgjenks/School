package edu.palindrome.page.javin;

import edu.jenks.dist.palindrome.*;
/**
 * Write a description of class PalindromeChecker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PalindromeChecker extends AbstractPalindromeChecker
{
    public static void main(String[] args){
        System.out.println("Begin");
        PalindromeChecker pc = new PalindromeChecker();
        System.out.println(pc.isPalindrome("Hello a 'o ' lleh"));
        System.out.println(pc.isPalindrome("waaaa,,3"));
        System.out.println("End without error");
    }
    public boolean isPalindrome(String arg){
        String w = "";
        arg = arg.replaceAll("\\W", "");
        if(arg.equals("")){
            return false;
        }    
        arg = arg.toLowerCase();
        for(int n = arg.length(); n > 0; n--){
            w += arg.charAt(n - 1);
        }
        if(w.equals(arg)){
            return true;
        }
        return false;
    }    
}
