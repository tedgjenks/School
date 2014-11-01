package edu.rogers.payton.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches implements Searcher{

	@Override
	public SearchResults binarySearch(Comparable[] elements, Object target)
			throws IllegalArgumentException {
		if (elements == null)
			throw new IllegalArgumentException();
		
		SearchResults sr = new SearchResults();
		sr.setDataStructureIndex(-1);
		boolean foundIt = false;
		int comparisons = 0;		
		
		int lowerLimit = 0;
		int maxIterations = (int) Math.ceil(Math.log(elements.length)/Math.log(2));
		int iterations = 0;
		int higherLimit = elements.length - 1;
		int middleIndex = higherLimit / 2;
		double compareToNumber;
		while (lowerLimit <= higherLimit) {
			if (maxIterations < iterations)
				throw new IllegalStateException("Too many iterations");
			middleIndex = (higherLimit + lowerLimit) / 2;
            comparisons++;
            compareToNumber = elements[middleIndex].compareTo(target); 
			if (compareToNumber == 0) {
				foundIt = true;
				break;
			} else if (compareToNumber > 0) {
            	higherLimit = middleIndex - 1;
            	continue;
            } else {
            	lowerLimit = middleIndex + 1;
            	continue;
            } 
		}

		sr.setComparisons(comparisons);
		sr.setElementFound(foundIt);
		if (foundIt) sr.setDataStructureIndex(middleIndex);
		sr.setDataStructureSize(elements.length);
		
		return sr;
	}

	@Override
	public SearchResults sequentialSearch(Object[] elements, Object target)
			throws IllegalArgumentException {
		if (elements == null)
			throw new IllegalArgumentException();
		
		SearchResults sr = new SearchResults();
		sr.setDataStructureIndex(-1);
		boolean foundIt = false;
		int comparisons = 0;
		int index;
				
		for (index = 0; index < elements.length; index++) {
			comparisons++;
			if (elements[index].equals(target)) {
				foundIt = true;
				break;
			}
		}
		
		sr.setComparisons(comparisons);
		sr.setElementFound(foundIt);
		if (foundIt) sr.setDataStructureIndex(index);
		sr.setDataStructureSize(elements.length);
				
		return sr;
	}

}
