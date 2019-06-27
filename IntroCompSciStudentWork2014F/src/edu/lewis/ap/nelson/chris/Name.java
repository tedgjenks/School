/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lewis.ap.nelson.chris;
import edu.jenks.dist.lewis.ap.*;
/**
 *
 * @author chris
 */
public class Name extends AbstractName{

    /**
     * @param args the command line arguments
     */
    
    public Name(String firstName, String lastName){
        super(firstName, lastName);
    }
    public static void main(String[] args) {
        Name test = new Name("Test","Test");
        Name nm = new Name("Test", "Test");
        System.out.println(test.compareTo(nm));
    }

    public int compareTo(AbstractName arg0){
       
       String first1 = this.getFirst();
       String first2 = arg0.getFirst();
       String last1 = this.getLast();
       String last2 = arg0.getLast();
       int charIndex = 0;
       int diff = 0;
       int diff2 = 0;
       
        for(int i = 0; i<last1.length(); i++){
            char currentChar1 = last1.charAt(charIndex);
            char currentChar2 = last2.charAt(charIndex);
            diff = Character.compare(currentChar1, currentChar2);
            if(diff!=0){
                System.out.println(this.getDuplicates());
                return diff;
            }
        }
        charIndex = 0;
        if(diff == 0){
            for(int i = 0; i<first1.length(); i++){
                char currentChar1 = first1.charAt(charIndex);
                char currentChar2 = first2.charAt(charIndex);
                diff2 = Character.compare(currentChar1, currentChar2);
                if(diff2!=0){
                    System.out.println(this.getDuplicates());
                    return diff2;
                }
            }
        }
        if(diff ==0 && diff2 ==0){
            this.setDuplicates(this.getDuplicates()+1);
        }
      System.out.println(this.getDuplicates());
      return diff;
    }
    public String toString(){
        String first = this.getFirst();
        String last = this.getLast();
        return last + ", " + first;
    }
}
