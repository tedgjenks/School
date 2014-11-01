package edu.wicker.marshall.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches implements Searcher{

	@SuppressWarnings("unchecked")
	@Override
	public SearchResults binarySearch(Comparable[] inputArray, Object toFind) throws IllegalArgumentException {
		SearchResults returnVal = new SearchResults();
		returnVal.setElementFound(false);
		returnVal.setDataStructureSize(inputArray.length);
		int curIndex = 0;
		boolean notFound = true;
		int amountSearches = 0;
		int lowIndex = 0;
		int highIndex = inputArray.length;
		while (notFound){
			curIndex = (lowIndex + highIndex) / 2;
			if(inputArray[curIndex] != null){
				if(inputArray[curIndex].compareTo(toFind) < 0){
					lowIndex = curIndex;
				}
				else if(inputArray[curIndex].compareTo(toFind) > 0){
					highIndex = curIndex;
				}
				else{
					returnVal.setDataStructureIndex(curIndex);
					returnVal.setElementFound(true);
					notFound = false;
				}
				amountSearches++;
				if (amountSearches > (int)Math.ceil((Math.log(inputArray.length) / Math.log(2.0)))){
					notFound = false;
				}
			}
			else{
				throw new IllegalArgumentException("Invalid Index @ " + amountSearches);
			}
		}
		returnVal.setComparisons(amountSearches);
		return returnVal;
	}

	@Override
	public SearchResults sequentialSearch(Object[] inputArray, Object toFind) throws IllegalArgumentException {
		SearchResults returnVal = new SearchResults();
		returnVal.setElementFound(false);
		returnVal.setDataStructureSize(inputArray.length);
		int i = 0;
		for(i = 0 ; i < inputArray.length ; i++){
			if(!(inputArray[i] == null)){
				if (inputArray[i].equals(toFind)){
					returnVal.setElementFound(true);
					returnVal.setDataStructureIndex(i);
					break;
				}
			}
			else{
				throw new IllegalArgumentException("Invalid Index @ " + i);
			}
		}
		returnVal.setComparisons(i + 1);
		return returnVal;
	}

}
