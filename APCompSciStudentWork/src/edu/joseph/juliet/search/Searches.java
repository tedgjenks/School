package edu.joseph.juliet.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches  implements Searcher{
	
	public Searches(){
	}
	public SearchResults binarySearch(Comparable[] array, Object arg1){
		if (arg1 == null)	
			throw new IllegalArgumentException();
		Comparable target = (Comparable) arg1;
		SearchResults sr1 = new SearchResults(array.length);
		
		sr1.setElementFound(false);
		
		int x =0;
		int low=0;
		int high = array.length-1;
		int mid = (low+high)/2;
		
		while(low<=high && !(array[mid].compareTo(target)==0)){
			if (target.compareTo(array[mid]) < 0){ //left half of array
				high = mid-1;
				x++;
			}
			else { //right half of array
				low = mid +1;
				x++;
			}
			mid=(low+high)/2;
		}
		if(target.compareTo(array[mid])==0){ //if element found
			sr1.setDataStructureIndex(mid);
			sr1.setElementFound(true);
			x++;
		}
		else{
			sr1.setDataStructureIndex(-1);
			sr1.setElementFound(false);
		}
		sr1.setComparisons(x);
		return sr1;
	}

	public SearchResults sequentialSearch(Object[] array, Object target){
		if(target==null)
			throw new IllegalArgumentException();
		SearchResults sr2 = new SearchResults(array.length);
		int x =0;
		for(int index=0; index <array.length; index++){
			if (array[index].equals(target)) {
				sr2.setElementFound(true);
				sr2.setDataStructureIndex(index);
				x++;
				sr2.setComparisons(x);
				return sr2;
			}
			else{
				x++;
				sr2.setDataStructureIndex(-1);
			}	
		}
		sr2.setComparisons(x);
		return sr2;
	}
}
