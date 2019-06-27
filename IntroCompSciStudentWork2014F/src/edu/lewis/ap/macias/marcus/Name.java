package edu.lewis.ap.macias.marcus;

import edu.jenks.dist.lewis.ap.AbstractName;

public class Name extends AbstractName
{
    public Name(String firstName, String lastName) {
        super(firstName, lastName);
    }
    //Compare by last name. If last name is the same, compare by first name.
    //If last name and first name are the same, increment duplicates
    public int compareTo(AbstractName arg0) {
        String first = this.getFirst();
        String second = this.getLast();
        String third = arg0.getFirst();
        String fourth = arg0.getLast();
        int returnVal = 0;
        //String randomThing = "A";
        //String randomThing2 = "Z";
        //int test = randomThing.compareTo(randomThing2); 
        //System.out.println(test);
        if(getLast().equals(arg0.getLast())) {
            if(getFirst().equals(arg0.getFirst())){
                setDuplicates(getDuplicates() + 1);
                return 0;
            }
            returnVal = first.compareTo(third);
            return returnVal;
        }
        returnVal = second.compareTo(fourth);
        
        return returnVal;
    }

    public String toString() {
        String answer = this.getLast() + ", " + this.getFirst();
        //return super.toString();
        return answer;
    }

    public static void main(String[] args) {
        
        AbstractName op1 = new Name("Marcus","Macias");
        AbstractName op2 = new Name("Bruce","Wayne");
        int answer = op1.compareTo(op2);
        System.out.println(answer);
    }

}
