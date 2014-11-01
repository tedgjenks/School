package edu.lodge.kaleb.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches implements Searcher {

	public Searches() {
	}

	@Override
	public SearchResults binarySearch(Comparable[] elements, Object target)
			throws IllegalArgumentException {
		if (elements == null || target == null){
			throw new IllegalArgumentException("neither the Object[] or target can be null");
		}
		SearchResults result = new SearchResults();
		int comparisons = 0, first = 0, last = elements.length-1, middle = (first + last)/2;
		while (elements[middle].compareTo(target) != 0 && first < last){
			comparisons++;
			if (elements[middle].compareTo(target) > 0){
				last = middle;
			} else if(elements[middle].compareTo(target) == 0) {
				break;
			} else {
				first = middle;
			}
		}
		if (elements[middle].compareTo(target) == 0){
			result.setComparisons(comparisons);
			result.setDataStructureIndex(middle);
			result.setDataStructureSize(elements.length);
			result.setElementFound(true);
		} else {
			result.setElementFound(false);
		}
		return result;
	}

	@Override
	public SearchResults sequentialSearch(Object[] elements, Object target)
			throws IllegalArgumentException {
		if (elements == null){
			throw new IllegalArgumentException("Object[] cannot be null");
		}
		SearchResults result = new SearchResults();
		int comparisons = 0;
		for (int x = 0; x < elements.length; x++){
			comparisons++;
			if(elements[x].equals(target)){
				result.setComparisons(comparisons);
				result.setDataStructureIndex(x);
				result.setDataStructureSize(elements.length);
				result.setElementFound(true);
			}
		}
		return result;
	}

}
