package edu.jenks.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

/**
 * Search for an item using the desired search algorithm.<br/>
 * Stores metrics about the search.
 * 
 * @author Ted Jenks
 *
 */
public class Searches implements Searcher {

	@Override
	public SearchResults sequentialSearch(Object[] elements, Object target) throws IllegalArgumentException {
		if(elements == null)
			throw new IllegalArgumentException("elements may not be null");
		SearchResults sr = new SearchResults(elements.length);
		int comparisons = 0;
		boolean found = false;
		for(int index = 0; index < elements.length && !found; index++, comparisons++) {
			if(targetEqualsElement(elements[index], target)) {
				found = true;
				sr.setDataStructureIndex(index);
			}
		}
		sr.setComparisons(comparisons);
		sr.setElementFound(found);
		return sr;
	}

	@Override
	public SearchResults binarySearch(Comparable[] elements, Object target)	throws IllegalArgumentException {
		if(elements == null)
			throw new IllegalArgumentException("elements may not be null");
		if(target == null)
			throw new IllegalArgumentException("target may not be null");
		SearchResults sr = new SearchResults(elements.length);
		boolean found = false;
		int comparisons = 0;
		int low = 0, high = elements.length - 1, middle = (low + high) / 2;
		while(!found && low <= high) {
			comparisons++;
			int compareValue = elements[middle].compareTo(target);
			if(compareValue == 0) {
				found = true;
				sr.setDataStructureIndex(middle);
			} else {
				if(compareValue < 0)
					low = middle + 1;
				else
					high = middle - 1;
				middle = (low + high) / 2;
			}
		}
		sr.setElementFound(found);
		sr.setComparisons(comparisons);
		return sr;
	}
	
	private boolean targetEqualsElement(Object element, Object target) {
		return !(target == null ^ element == null) && ((target == null && element == null) || target.equals(element));
	}
}
