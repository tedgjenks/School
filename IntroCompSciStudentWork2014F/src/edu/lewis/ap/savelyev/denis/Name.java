package edu.lewis.ap.savelyev.denis;

import edu.jenks.dist.lewis.ap.*;

public class Name extends AbstractName {
    public static void main(String[] args) {
        Name instance = new Name("John", "Doe");
        Name instance1 = new Name("Popp", "Man");
        System.out.println(instance.compareTo(instance1));
    }
    public Name(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public int compareTo(AbstractName arg0) {
        int returnVal = 0;
        if (getLast().equals(arg0.getLast())) {
            if (getFirst().equals(arg0.getFirst())){
                setDuplicates(getDuplicates() + 1);
                returnVal = this.getFirst().compareTo(arg0.getFirst());
                return returnVal;
            }
        } 
        returnVal = this.getLast().compareTo(arg0.getLast());
        return returnVal;
    }
    public String toString() {
        return getLast() + ", " + getFirst();
    }
    
}
