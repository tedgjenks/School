package edu.lewis.ap.driscoll.aidan;

import edu.jenks.dist.lewis.ap.*;
/**
 * Write a description of class Name here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Name extends AbstractName
{
    public static void main(String[] args) {
        System.out.println("Begin");
        
        System.out.println("End");
    }
    
    public Name(String firstName, String lastName){
        super(firstName, lastName);
        setFirst(firstName);
        setLast(lastName);
    }
    
    public int compareTo(AbstractName arg0) {
        int returnVal = 0;
        String first = this.getFirst();
        String last = this.getLast();
        String twoLast = arg0.getLast();
        String twoFirst = arg0.getFirst();
	if(getLast().equals(arg0.getLast())){
	    if(getFirst().equals(arg0.getFirst())){
	        setDuplicates(getDuplicates() + 1);
	        return 0;
	    }
	    returnVal = first.compareTo(twoFirst);
	    return returnVal;
	}
	returnVal = last.compareTo(twoLast);
	return returnVal;
    }
    
    public String toString() {
	return this.getLast() + ", " + this.getFirst();
    }
}
