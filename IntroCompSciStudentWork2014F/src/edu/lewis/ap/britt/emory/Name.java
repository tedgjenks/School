package edu.lewis.ap.britt.emory;
import edu.jenks.dist.lewis.ap.AbstractName;

public class Name extends AbstractName {
    public Name(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public int compareTo(AbstractName arg0) {
        String fName = this.getFirst();
        String lName = this.getLast();
        String compareFName = arg0.getFirst();
        String compareLName = arg0.getLast(); 
        if(lName.equals(compareLName)){
            if(fName.equals(compareFName)){
                setDuplicates(getDuplicates() + 1);
                return 0; 
            }
            int returnVal = fName.compareTo(compareFName);  
            return returnVal; 
        }
        int returnVal = lName.compareTo(compareLName);
        return returnVal; 
    }
    public String toString() {
        String fName = this.getFirst();
        String lName = this.getLast();
        return lName + ", " + fName;
    }
}
