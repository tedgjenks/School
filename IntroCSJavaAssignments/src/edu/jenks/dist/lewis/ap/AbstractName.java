/**
 * 
 */
package edu.jenks.dist.lewis.ap;

/**
 * Names are ordered alphabetically. Last name is compared first, then first name.
 * @author Ted Jenks
 *
 */
public abstract class AbstractName implements Comparable<AbstractName> {
	
	private String first, last;
	private int duplicates;	

	/**
	 * <b>precondition: </b><code>firstName</code> and <code>lastName</code> are not null.
	 * @param firstName
	 * @param lastName
	 */
	public AbstractName(String firstName, String lastName) {
		first = firstName;
		last = lastName;
	}
	
	/**
	 * @return the number of comparisons made where the last and first names were the same
	 */
	public int getDuplicates() {
		return duplicates;
	}

	/**
	 * @param duplicates
	 */
	public void setDuplicates(int duplicates) {
		this.duplicates = duplicates;
	}

	/**
	 * @return
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * <b>precondition: </b><code>first</code> is not null.
	 * @param first
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * @return
	 */
	public String getLast() {
		return last;
	}

	/**
	 * <b>precondition: </b><code>last</code> is not null.
	 * @param last
	 */
	public void setLast(String last) {
		this.last = last;
	}

}
