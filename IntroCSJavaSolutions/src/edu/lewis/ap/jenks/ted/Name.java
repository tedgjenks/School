/**
 * 
 */
package edu.lewis.ap.jenks.ted;

import edu.jenks.dist.lewis.ap.AbstractName;

/**
 * @author Ted Jenks
 *
 */
public class Name extends AbstractName {

	/**
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractName arg0) {
		int compare = getLast().compareTo(arg0.getLast());
		if(compare == 0)
			compare = getFirst().compareTo(arg0.getFirst());
		if(compare == 0)
			setDuplicates(getDuplicates() + 1);
		return compare;
	}

	/**
	 * @return last name comma space first name; e.g. Smith, John
	 */
	@Override
	public String toString() {
		return getLast() + ", " + getFirst();
	}
	
	

}
