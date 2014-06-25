/**
 * 
 */
package edu.jenks.array.dist;

/**
 * @author JenksT
 * See the documentation for the corresponding methods in java.util.List
 */
public interface List {
	boolean add(Object element); // TODO change to match java.util.List
	void add(int index, Object element);
	void clear();
	Object get(int index);
	boolean isEmpty();
	Object remove(int index);
	Object set(int index, Object element);
	int size();
	Object[] toArray();
}
