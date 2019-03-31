package edu.palindrome.britt.emory;
import edu.jenks.dist.palindrome.*;

/**
 * Write a description of class PalindromeChecker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PalindromeChecker extends AbstractPalindromeChecker {
    public static void main(String[] args){
       System.out.println("begin");
       PalindromeChecker pc = new PalindromeChecker();
       boolean act = pc.isPalindrome("mom");
       boolean exp = true;
       assert act == exp : "isPalindrome exp: " + exp + "; actual: " + act;
       boolean act2 = pc.isPalindrome("word");
       boolean exp2 = false; 
       assert act2 == exp2 : "isPalindrome exp: " + exp2 + "; actual: " + act2;
       boolean act3 = pc.isPalindrome("Mom");
       boolean exp3 = true;
       assert act3 == exp3 : "isPalindrome exp: " + exp3 + "; actual: " + act3;
       boolean act4 = pc.isPalindrome("%%");
       boolean exp4 = false;
       assert act4 == exp4 : "isPalindrome exp: " + exp4 + "; actual: " + act4;
       System.out.println("End without error");
    }
    public boolean isPalindrome(String arg){
        String argFlip = "";
        for(int i = arg.length() - 1; i >= 0; i--){
            argFlip += arg.charAt(i);
        }
        String argFlipAlph = argFlip.replaceAll("[^a-zA-Z0-9]","");
        String argAlph = arg.replaceAll("[^a-zA-Z0-9]","");
        if(argAlph.equals("")){
            return false;
        }
        if(argFlipAlph.equalsIgnoreCase(argAlph)){
            return true;
        }
        
        return false;
    }
}
