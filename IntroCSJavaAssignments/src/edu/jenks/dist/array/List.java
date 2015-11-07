/**
 * 
 */
package edu.jenks.dist.array;

/**
 * <p>See the documentation for the corresponding methods in java.util.List</p>
 * Note that you do not have to implement generics or the associated syntax.<br>
 * @author JenksT
 */
public interface List {
	boolean add(Object element);
	void add(int index, Object element) throws IndexOutOfBoundsException;
	void clear();
	Object get(int index) throws IndexOutOfBoundsException;
	boolean isEmpty();
	Object remove(int index) throws IndexOutOfBoundsException;
	Object set(int index, Object element) throws IndexOutOfBoundsException;
	int size();
	Object[] toArray();
}
