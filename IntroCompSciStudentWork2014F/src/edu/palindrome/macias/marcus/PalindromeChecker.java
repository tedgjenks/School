package edu.palindrome.macias.marcus;


import edu.jenks.dist.palindrome.*;

public class PalindromeChecker extends AbstractPalindromeChecker
{
    public static void main(String[] args){
        System.out.println("Begin");
        PalindromeChecker palindrome = new PalindromeChecker();
        System.out.println(palindrome.isPalindrome("%%"));
        
    }
    public boolean isPalindrome(String arg){
        
        //int number = Integer.parseInt(arg);
        //System.out.println(number);
        //String pal = "";
        /*for(int i = 0 ; i < pal.length() ; i++){
            //pal.charAt(i);
            if(arg.charAt(i).equals("i")){
            
            }
        }*/
        String pal = arg;
        /*char[] actualPal = arg.toCharArray();
        //System.out.println(actualPal);
        for(int i = 0 ; i < actualPal.length; i++){
            if(actualPal[i]!=' '){
                pal += actualPal[i];
            }
        }*/
        pal = pal.toLowerCase();
        pal = pal.replaceAll("[^a-zA-Z_0-9]","");
        if(pal.length() == 0){
            return false;
        }
        //System.out.println(pal);
        //System.out.println(pal.length());
        if (pal.length() % 2 == 0){
            String begining = pal.substring(0,pal.length() / 2);
            //System.out.println(begining);
            String end = pal.substring(pal.length() / 2, pal.length());
            //System.out.println(end);
            String actualEnd = "";
            for(int i = end.length() - 1; i >= 0;i--){
                actualEnd += end.charAt(i);
            }
            
            if(begining.equals(actualEnd)){
                return true;
            }else{
                return false;
            }
        }else if(pal.length() % 2 != 0){
            String begining = pal.substring(0,pal.length() / 2);
            //System.out.println(begining);
            String middle = pal.substring(pal.length() / 2, pal.length() / 2 + 1);
            String end = pal.substring(pal.length() / 2 + 1 , pal.length());
            String actualEnd = "";
            for(int i = end.length() - 1; i >= 0;i--){
                actualEnd += end.charAt(i);
            }
            if(begining.equals(actualEnd)){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }
}
