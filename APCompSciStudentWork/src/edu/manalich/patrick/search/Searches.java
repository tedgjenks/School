
package edu.manalich.patrick.search;

import edu.jenks.search.dist.*;

public class Searches implements Searcher {
	
	@Override
	public SearchResults binarySearch(Comparable[] elements, Object target){
		if (target == null)	
			throw new IllegalArgumentException();
		
		int comparisons = 0;
		
		SearchResults sr = new SearchResults(elements.length);
		
		int low = 0, high = elements.length - 1, mid = (high + low)/2;
		
		while ( !(elements[mid].compareTo(target) == 0) && low <= high){
			if(elements[mid].compareTo(target) < 0){
				low = mid + 1;
				comparisons++;
			}
			else {
				high = mid - 1;
				comparisons++;
			}
			mid = (high + low) / 2;
			
		}
		if (elements[mid].compareTo(target) == 0){
			sr.setDataStructureIndex(mid);
			sr.setElementFound(true);
			comparisons++;
		}
			
		else{
			sr.setDataStructureIndex(-1);
			sr.setElementFound(false);
		}
		sr.setComparisons(comparisons);
		return sr;
	}
	
	
	
	

	@Override
	public SearchResults sequentialSearch(Object[] elements, Object target){
		if (target == null)	
				throw new IllegalArgumentException(); 
			
		int comparisons = 0;
		
			SearchResults sr = new SearchResults(elements.length);
		
			for (int i = 0; i < elements.length; i++){
				if (elements[i].equals(target)){
					sr.setDataStructureIndex(i);
					sr.setElementFound(true);
					comparisons++;
					sr.setComparisons(comparisons);
					return sr;
				}
				else{
					sr.setDataStructureIndex(-1);
					sr.setElementFound(false);
					comparisons++;
				}
			}
			sr.setComparisons(comparisons);
			return sr;
			
	}

}
