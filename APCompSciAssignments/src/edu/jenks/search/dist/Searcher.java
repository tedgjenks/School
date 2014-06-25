package edu.jenks.search.dist;

/**
 * @author Ted Jenks
 *
 */
public interface Searcher {

	/**
	 * Performs a sequential search on elements to find target.<br/>
	 * 
	 * @param elements the data structure to search
	 * @param target the element to find
	 * @return information about the search
	 * @throws IllegalArgumentException if elements is null
	 */
	SearchResults sequentialSearch(Object[] elements, Object target) throws IllegalArgumentException;
	
	/**
	 * Performs a binary search on elements to find target.<br/>
	 * Precondition:<br/>
	 * 1) elements are in ascending order.<br/>
	 * 2) target is an acceptable argument for the compareTo method of each element in elements.<br/>
	 * 
	 * @param elements the data structure to search
	 * @param target the element to find
	 * @return information about the search
	 * @throws IllegalArgumentException if elements or target is null
	 */
	SearchResults binarySearch(Comparable[] elements, Object target) throws IllegalArgumentException;
}
