package edu.lewis.ap.hollingsworth.james;

import edu.jenks.dist.lewis.ap.AbstractName;

public class Name extends AbstractName {

    public Name(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int compareTo(AbstractName arg0) {
        if(getLast().equals(arg0.getLast())) {
            if(getFirst().equals(arg0.getFirst()))
                setDuplicates(getDuplicates() + 1);
        }
        int index = 0;
        int longest = getLast().length();
        boolean lastNameIsEqual = false,
                firstNameIsEqual = false;
        if(arg0.getLast().length() > longest) longest = arg0.getLast().length();
        while(index < longest) {
            if(index >= getLast().length() || index >= arg0.getLast().length() || (int) getLast().charAt(index) - (int) arg0.getLast().charAt(index) != 0) break;
            index++;
        }
        if(index >= longest) {
            lastNameIsEqual = true;
            index = 0;
            longest = getFirst().length();
            if(arg0.getFirst().length() > longest) longest = arg0.getFirst().length();
            while(index < longest) {
                if(index >= getFirst().length() || index >= arg0.getFirst().length() || (int) getFirst().charAt(index) - (int) arg0.getFirst().charAt(index) != 0) break;
                index++;
            }
        }
        if(!lastNameIsEqual)
            return (int) getLast().charAt(index) - (int) arg0.getLast().charAt(index);
        else if(index < longest)
            return (int) getFirst().charAt(index) - (int) arg0.getFirst().charAt(index);
        return 0;
    }

    public String toString() {
        return getLast() + ", " + getFirst();
    }

    public static void main(String[] args) {
        Name n = new Name("a", "a");
        System.out.println(n.compareTo(new Name("a", "a")));
    }

}
