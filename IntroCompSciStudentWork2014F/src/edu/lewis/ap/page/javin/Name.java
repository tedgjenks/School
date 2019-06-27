package edu.lewis.ap.page.javin;
import edu.jenks.dist.lewis.ap.*;
public class Name extends AbstractName {
	char[] alphabet = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public static void main (String[] args) {
		Name nom = new Name("First", "Last");
		Name notNom = new Name("Firsti", "Last"); 
		System.out.println(nom.compareTo(notNom));
	}
	
	public Name(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public int compareTo(AbstractName o) {
		int returnVal;
		if(getLast().equals(o.getLast())) {
			if(getFirst().equals(o.getFirst())) {
				setDuplicates(getDuplicates() + 1);
				return 0;
			}
			returnVal =  getFirst().compareTo(o.getFirst());
		}else {
			returnVal =  getLast().compareTo(o.getLast());
		}
		return returnVal;
	}
	public String toString() {
		return getLast() + ", " + getFirst();
	}
}
