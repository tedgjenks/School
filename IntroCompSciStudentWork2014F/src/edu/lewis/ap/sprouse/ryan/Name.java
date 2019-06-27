package edu.lewis.ap.sprouse.ryan;
import edu.jenks.dist.lewis.ap.*;
public class Name extends AbstractName
{
    public Name(String firstname, String lastname){
        super(firstname, lastname);
    }
    public int compareTo(AbstractName arg0){
        String firstName1 = this.getFirst();
        String lastName1 = this.getLast();
        String firstName2 = arg0.getFirst();
        String lastName2 = arg0.getLast();
        int returnValue = 0;
        if(lastName1.equals(lastName2)){
            if(firstName1.equals(firstName2)){
                setDuplicates(this.getDuplicates() + 1);
                return 0;
            }
            returnValue = firstName1.compareTo(firstName2);
        }else{
            returnValue = lastName1.compareTo(lastName2);
        }
        return returnValue;
    }
    public String toString(){
        String firstName = this.getFirst();
        String lastName = this.getLast();
        return lastName + ", " + firstName;
    }
}