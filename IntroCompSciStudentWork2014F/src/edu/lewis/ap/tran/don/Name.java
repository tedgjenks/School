package edu.lewis.ap.tran.don;

import edu.jenks.dist.lewis.ap.*;

public class Name extends AbstractName
{
    public Name(String firstName,String lastName) {
        super(firstName, lastName);
        setFirst(firstName);
        setLast(lastName);
    }
    public static void main(String[] arg) {
        String whatever = "dac";
        String whateverTwo = "dab";
        int comp = whateverTwo.compareTo(whatever);
        System.out.println(comp);
    }
    public int compareTo(AbstractName arg0) {
        String thisLast = this.getLast();
        String thisFirst = this.getFirst();
        String argLast= arg0.getLast();
        String argFirst = arg0.getFirst();
        int comp = 0;
        if(thisLast.equals(argLast)) {
            if(thisFirst.equals(argFirst)) {
                setDuplicates(getDuplicates() + 1);
                return 0;
            }
            comp = thisFirst.compareTo(argFirst);
            if(comp > 0) {
                return 1;
            }
            return -1;
        }
        comp = thisLast.compareTo(argLast);
        if(comp > 0) {
            return 1;
        }
        if(comp < 0) {
            return -1;
        }
        return 0;
    }
    public String toString() {
        return this.getLast() + ", " + this.getFirst();
    }
    
}
