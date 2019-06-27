package edu.lewis.ap.crosby.amber;

import edu.jenks.dist.lewis.ap.AbstractName;

public class Name extends AbstractName {
    public static void main(String[] args) {
        
    }
    
    public Name(String firstName, String lastName) {
        super(firstName, lastName);
        setFirst(firstName);
        setLast(lastName);
        setDuplicates(0);
    }
    
    public int compareTo(AbstractName arg0) {
        int duplicates = getDuplicates();
        if(getLast().equals(arg0.getLast())){
            if(getFirst().equals(arg0.getFirst())){
                duplicates += 1;
                setDuplicates(duplicates);
                return 0;
            }
            int returnVal = getFirst().compareTo(arg0.getFirst());
            return returnVal;
        }
        int returnVal = getLast().compareTo(arg0.getLast());
        return returnVal;
    }
    
    public String toString() {
       return getFirst() + ", " + getLast();
    }
}
