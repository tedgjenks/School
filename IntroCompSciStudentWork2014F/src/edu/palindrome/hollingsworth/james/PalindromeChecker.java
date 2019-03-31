package edu.palindrome.hollingsworth.james;
import edu.jenks.dist.palindrome.AbstractPalindromeChecker;

public class PalindromeChecker extends AbstractPalindromeChecker {
    public static void main(String args[]) {
        PalindromeChecker pal = new PalindromeChecker();
        System.out.println(pal.isPalindrome("mom"));
        System.out.println(pal.isPalindrome("Mom"));
        System.out.println(pal.isPalindrome("Dad!"));
        System.out.println(pal.isPalindrome("A Man, a plan, a canal: Panama"));
        System.out.println(pal.isPalindrome("Rude"));
        System.out.println(pal.isPalindrome("%%"));
    }
    
    public boolean isPalindrome(String arg) {
        String temp = "";
        arg = arg.toLowerCase();
        for(int i = 0; i < arg.length(); i++) {
            if(Character.isLetter(arg.charAt(i)))
                temp += arg.charAt(i);
        }
        if(temp.length() <= 1) return false;
        for(int i = 0; i < (int) (arg.length() / 2); i++) {
            if(temp.charAt(i) == temp.charAt((temp.length() - 1) - i))
                continue;
            else return false;
        }
        return true;
    }
}
