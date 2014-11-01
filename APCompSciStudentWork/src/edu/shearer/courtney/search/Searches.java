package edu.shearer.courtney.search;

import edu.jenks.search.dist.Searcher;
import edu.jenks.search.dist.SearchResults;

public class Searches extends Object implements Searcher {
	public Searches() {
	}
	
	
	
	public SearchResults sequentialSearch(Object[] elements, Object target) 
			throws IllegalArgumentException{
		if(elements == null){
            throw new IllegalArgumentException();
		} 
		SearchResults sr = new SearchResults();
		int dataStructureSize= elements.length;
		sr.setDataStructureSize(dataStructureSize);
		sr.setDataStructureIndex(-1);
		 
		int comp = 0;
		for(int index = 0; index < elements.length; index++, comp++){
			
			if((elements[index]).equals(target)){
				sr.setElementFound(true);
				sr.setDataStructureIndex(index);
				comp++;
				sr.setComparisons(comp);
				return sr;
				
				
				}
			
			
			
			
		}
		
		sr.setComparisons(comp);
		return sr;
		
              
              
     }
	public SearchResults binarySearch(Comparable[] elements, Object target)
              throws IllegalArgumentException{
		if(elements == null || target == null){
			throw new IllegalArgumentException();
		}
		SearchResults bSR = new SearchResults();
		int dataStructureSize = elements.length;
		bSR.setDataStructureSize(dataStructureSize);
		bSR.setDataStructureIndex(-1);
		
		int low = 0;
		int high = elements.length -1;
		int mid = (low +high)/2;
		int comp = 0;
		
		while((elements[mid]).compareTo(target) != 0 && low <= high){
			if((elements[mid]).compareTo(target) > 0){
				high = mid - 1;
			}
			else{
				low = mid + 1;
			}
			mid = (low + high)/2;
			comp++;
			
		}
		if((elements[mid]).compareTo(target) == 0){
			 comp++;
			bSR.setElementFound(true);
			bSR.setDataStructureIndex(mid);
			
		}
		bSR.setComparisons(comp);
		return bSR;
	}

}
