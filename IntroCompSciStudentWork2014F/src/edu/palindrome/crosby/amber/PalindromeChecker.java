package edu.palindrome.crosby.amber;

import edu.jenks.dist.palindrome.*;

public class PalindromeChecker extends AbstractPalindromeChecker
{
    public static void main(String[] args){
        System.out.println("Begin");
        PalindromeChecker pc = new PalindromeChecker();
        String test = "A Man, a plan, a canal: Panama.";
        System.out.println(pc.isPalindrome (test));
        System.out.println("End without error");
    }
    public boolean isPalindrome(String arg){
        arg = arg.toLowerCase();
        String newArg = "";
        for(int index = 0; index < arg.length(); index++){
            char c = arg.charAt(index);
            if(Character.isLetterOrDigit(c)){
                newArg += c;
            }
        }
        System.out.println(newArg);
        int lowIndex = 0;
        int highIndex = newArg.length() -1;
        while(lowIndex<=highIndex){
            char l = newArg.charAt(lowIndex);
            char h = newArg.charAt(highIndex);
            if(l==h){
                lowIndex+=1;
                highIndex-=1;
            }else{
                return false;
            }
        }
        if(!newArg.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
