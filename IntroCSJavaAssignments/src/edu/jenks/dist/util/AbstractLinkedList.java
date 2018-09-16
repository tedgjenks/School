package edu.jenks.dist.util;

import java.util.NoSuchElementException;

public interface AbstractLinkedList<E> {
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param e - element to be appended to this list
	 * @return true (as specified by Collection.add(E))
	 */
	boolean add(E e);
	
	/**
	 * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 * 
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size())
	 */
	void add(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Inserts the specified element at the beginning of this list.
	 * 
	 * @param e - the element to add
	 */
	void addFirst(E e);
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param e - the element to add
	 */
	void addLast(E e);
	
	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	void clear();
	
	/**
	 * Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param o - element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	boolean contains(Object o);
	
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index - index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Returns the first element in this list.
	 * 
	 * @return the first element in this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	E getFirst() throws NoSuchElementException;
	
	/**
	 * Returns the last element in this list.
	 * 
	 * @return the last element in this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	E getLast() throws NoSuchElementException;
	
	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * 
	 * @param o - element to search for
	 * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	int indexOf(Object o);
	
	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * 
	 * @param o - element to search for
	 * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	int lastIndexOf(Object o);
	
	/**
	 * Retrieves and removes the head (first element) of this list.
	 * 
	 * @return the head of this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	E remove() throws NoSuchElementException;
	
	/**
	 * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 * 
	 * @param index - index of the element to remove
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	E remove(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	 * 
	 * @param o - element to be removed from this list, if present
	 * @return true if this list contained the specified element
	 */
	boolean remove(Object o);
	
	/**
	 * Removes and returns the last element from this list.
	 * 
	 * @return the last element from this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	E removeLast() throws NoSuchElementException;
	
	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 * 
	 * @param index - index of the element to replace
	 * @param element - element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	E set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * LinkedList(<code>size</code>) [value 1, value 2, ...]
	 * Each value should be followed by a comma and space, including the last value
	 */
	@Override
	String toString();
	
}
