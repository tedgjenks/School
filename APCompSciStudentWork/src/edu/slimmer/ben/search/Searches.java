package edu.slimmer.ben.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches
extends java.lang.Object
implements Searcher {

	@Override
	public SearchResults binarySearch(Comparable[] elements, Object target)
			throws IllegalArgumentException {
		SearchResults result= new SearchResults(elements.length);
		int low=0;
		int high= elements.length-1;
		int mid= (low+high)/2;
		int counter=1;
		
		while (elements[mid].equals(target)==false && low<=high) {
			if (((Comparable)target).compareTo(elements[mid])<0)
				high= mid-1;
			else
				low=mid+1;
			mid=(low+high)/2;
			counter++;
		}
		if (elements[mid].equals(target)) {
			result.setComparisons(counter);
			result.setDataStructureIndex(mid);
			result.setElementFound(true);
			return result;
		}
		result.setComparisons(counter);
		result.setElementFound(false);
		return result;
	}

	@Override
	public SearchResults sequentialSearch(Object[] elements, Object target)
			throws IllegalArgumentException {
		SearchResults result= new SearchResults(elements.length);
		int foundspot=-1;
		 for (int index= elements.length-1;index>0; index--) {
			if (target.equals(elements[index]))
					foundspot=index;
		}
		if (foundspot==-1) {
			result.setElementFound(false);
			result.setComparisons(elements.length);
		}
		else  {
			result.setElementFound(true);
			result.setComparisons(foundspot+1);
			result.setDataStructureIndex(foundspot);
		}
		return result;
	}
}
