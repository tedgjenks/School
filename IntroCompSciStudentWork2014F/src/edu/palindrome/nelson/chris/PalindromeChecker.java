/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.palindrome.nelson.chris;
import edu.jenks.dist.palindrome.*;
/**
 *
 * @author chris
 */

public class PalindromeChecker extends AbstractPalindromeChecker{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PalindromeChecker test = new PalindromeChecker();
        System.out.println(test.isPalindrome("%%"));
    }
    public boolean isPalindrome(String arg){
        int length = arg.length();
        String alphaString = "";
        String reversed = ""; 
        
        for(int a = 0; a<length; a++){
            char character = arg.charAt(a);
            boolean check = Character.isLetterOrDigit(character);
            if(check){
               alphaString += character;
            } 
        }
        if(alphaString.isEmpty()){
            return false; 
        }
        int alphaLength = alphaString.length();
        
        for(int i = alphaLength - 1; i>=0; i--){
            char character = alphaString.charAt(i);
            reversed += character; 
        }
        
     return reversed.equalsIgnoreCase(alphaString); 
    }
    
}
