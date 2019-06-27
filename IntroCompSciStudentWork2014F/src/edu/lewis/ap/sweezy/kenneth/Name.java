package edu.lewis.ap.sweezy.kenneth;

import edu.jenks.dist.lewis.ap.*;

public class Name extends AbstractName {
	public static void main(String[] args) {
		Name testing1 = new Name("Bill", "Cosbo");
		Name testing2 = new Name("Bill", "Cosby");
		System.out.println(testing1.compareTo(testing2));
	}
	
	public Name(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public int compareTo(AbstractName arg0) {
		if(getLast().equals(arg0.getLast())) {
			if(getFirst().equals(arg0.getFirst())) {
				setDuplicates(getDuplicates() + 1);
				return 0;
			}
			int temp1 = getFirst().compareTo(arg0.getFirst());
			return temp1;
		}
		int temp2 = getLast().compareTo(arg0.getLast());
		return temp2;
	}
	
	public String toString() {
		return getLast() + ", " + getFirst();
	}
	
}
