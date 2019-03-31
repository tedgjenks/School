package edu.palindrome.driscoll.aidan;

import edu.jenks.dist.palindrome.*;
/**
 * Write a description of class Palindrome here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PalindromeChecker extends AbstractPalindromeChecker
{
   public static void main(String[] args){
       System.out.println("Begin");
       PalindromeChecker pc = new PalindromeChecker();
       System.out.println(pc.isPalindrome("%%"));
       System.out.println("End Without Error");
   }
   
   public boolean isPalindrome(String arg){
      String reverse = "";
      arg = arg.toLowerCase();
      String forward = "";
      for(int i = arg.length() - 1; i >= 0; i--){
          char c = arg.charAt(i);
          if(Character.isLetterOrDigit(c)){
              reverse += c;
        }
      }
      for(int i = 0; i < arg.length(); i++){
          char c = arg.charAt(i);
          if(Character.isLetterOrDigit(c)){
              forward += c;
        }
      }
      System.out.println(reverse);
      System.out.println(forward);
      if(forward.equals(reverse)){
          if(forward != "" && reverse != ""){
          return true;
        }else{
          return false;
        }
      }else{
          return false;
      }
   }
}
