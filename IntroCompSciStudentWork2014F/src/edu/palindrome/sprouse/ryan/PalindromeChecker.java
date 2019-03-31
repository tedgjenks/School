package edu.palindrome.sprouse.ryan;

import edu.jenks.dist.palindrome.*;
/**
 * Write a description of class PalindromeChecker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PalindromeChecker extends AbstractPalindromeChecker
{
    
    
    // take the list and remove everything that isnt a letter. 
    // split the cleaned list into two halves, ignoring the middle letter if the list length is an odd number.
    // reverse the second half list, so that if the given string is a palindrome, the first and second half strings will be identical.
    // compare the first and second half strings, and if they are the same, return 

    
    public boolean isPalindrome(String arg){
        
        String cleanedList = cleanList(arg);
        
        // this section splits the given string into two halves. I decided to run this in the main function because it has to return two strings
        int listLength = cleanedList.length();
        
        int halfwayPoint = (listLength / 2);
        String firstHalf = "";
        //the secondHalf string will be in reverse order, so it will be easier to tell if it is equal to the first half eg. if the string was tacocat, the secondHalf string would be "tac"
        String secondHalf = "";
        if(listLength == 0){
            return false;
        }
        if(listLength == 1){
            return true;
        }
        if(listLength % 2 == 0){
            //move first half of tested string to a new string and test it against the the last half of the tested string
            //halfwayPoint = (listLength / 2);
            for(int i = 0; i <= halfwayPoint - 1; i++){
                firstHalf += cleanedList.charAt(i);
            }
            for(int i = listLength - 1; i >= halfwayPoint; i--){
                secondHalf += cleanedList.charAt(i);
            }
        }else{ // if the string is odd, divide the length of the string by two and add .5 to get the index of the letter that needs to be removed
            
            for(int i = 0; i <= halfwayPoint - 1; i++){
                firstHalf += cleanedList.charAt(i);
            }
            for(int i = listLength - 1; i >= halfwayPoint + 1; i--){
                secondHalf += cleanedList.charAt(i);
            }
        }
        
        // uses the compareLists function to determine if the two halves of the string are equal
        return compareLists(firstHalf, secondHalf);
    }
    private boolean compareLists(String firstHalf, String secondHalf){
        // using a for loop to iterate over the lists, compare each letter at every location to the corresponding letter on the other list. 
        // every time that the letters are the same, add 1 to a counter. 
        // when the for loop is finished, compare the counter to the length of the string, if they're the same, return true
        int counter = 0;
        for(int y = 0; y <= firstHalf.length() - 1; y++){
            if(firstHalf.charAt(y) == secondHalf.charAt(y)){
                counter++;
            }
        }
        if(counter == firstHalf.length()){
            return true;
        }else{
            return false;
        }
    }
    private String cleanList(String arg){
        String newList = "";
        for(int x = 0; x <= arg.length() - 1; x++){
            char current = arg.charAt(x);
            if((Character.isLetterOrDigit(current)) == true){
                // append the current character to the newList string
                newList += arg.charAt(x);
            }
            x++;
        }
        return newList.toLowerCase();
    }
    
}
