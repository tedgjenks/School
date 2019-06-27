package edu.lewis.ap.whitt.rose;

import edu.jenks.dist.lewis.ap.AbstractName;
/**
 * Write a description of class Name here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Name extends AbstractName
{
    public static void main(String[] args) {
        Name pc = new Name("Barack", "Obama");
        
        Name arg0 = new Name("Rose", "Whitt");
        System.out.println(pc.compareTo(arg0));
        System.out.println(pc.toString());
    }
    
    public Name (String firstName, String lastName) {
        super (firstName, lastName);
    }
    
    public int compareTo (AbstractName arg0) {
        int lastTest = getLast().compareTo(arg0.getLast());
        int firstTest = getFirst().compareTo(arg0.getFirst());
        if (lastTest == 0) {
            if (firstTest == 0) {
                setDuplicates(getDuplicates() + 1);
                return firstTest;
            } else {
                return firstTest;
            }
        } else {
            return lastTest;
        }
    }
    
    public String toString() {
        return getLast() + ", " + getFirst();
    }
}
