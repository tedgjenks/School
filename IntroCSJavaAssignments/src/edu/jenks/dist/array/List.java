/**
 * 
 */
package edu.jenks.dist.array;

/**
 * <p>See the documentation for the corresponding methods in <code>java.util.List</code> or <code>java.util.ArrayList</code></p>
 * Note that you do not have to implement generics or the associated syntax.<br>
 * An array should be used hold the data.<br>
 * When an element must be added and the array is full:<br>
 *  - A new array should be created. It's length should be <code>GROWTH_FACTOR</code> * original length.<br>
 *  - The old array should be copied into the new array, and then the element should be added.<br>
 * @author Ted Jenks
 */
public interface List {
	
	/**
	 * When a new backing array must be created, multiply this by current length to get the new length.
	 */
	public static final byte GROWTH_FACTOR = 2;
	
	/**
	 * @return the instance variable holding the data
	 */
	Object[] getBackingArray();
	
	/**
	 * @param element
	 * @return
	 */
	boolean add(Object element);
	
	/**
	 * @param index
	 * @param element
	 * @throws IndexOutOfBoundsException
	 */
	void add(int index, Object element) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 */
	void clear();
	
	/**
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	Object get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	Object remove(int index) throws IndexOutOfBoundsException;
	
	/**
	 * @param index
	 * @param element
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	Object set(int index, Object element) throws IndexOutOfBoundsException;
	
	/**
	 * @return
	 */
	int size();
	
	/**
	 * @return
	 */
	Object[] toArray();
}
