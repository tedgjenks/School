package edu.butler.julie.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches implements Searcher{

	
	
	public static void main(String[] args){
		String string1="apple";
		String string2="bat";
		String string3="cat";
		System.out.println(string3.compareTo(string1));
		System.out.println(string1.compareTo(string2));
		System.out.println(string2.compareTo(string2));
	}

	@Override
	public SearchResults binarySearch(Comparable[] arg0, Object arg1){
		SearchResults bs = new SearchResults();
		bs.setDataStructureSize(arg0.length);
		int comparisons=0;
		int upperBound=arg0.length;
		int lowerBound=0;
		bs.setElementFound(false);
		boolean found=false;
		int condition = (int)Math.ceil(Math.log(arg0.length)/Math.log(2));
		while(!found&&comparisons<=condition){
			int middle=(lowerBound+upperBound)/2;
			if(arg0[middle]==null)
				throw new IllegalArgumentException("Null Elements");
			if(arg0[middle].compareTo(arg1)==0){
				found=true;
				bs.setDataStructureIndex(middle);
				bs.setElementFound(true);
				comparisons++;
			}
			else if(arg0[middle].compareTo(arg1)>0){
				upperBound=middle;
				comparisons++;
			}
			else if(arg0[middle].compareTo(arg1)<0){
				lowerBound=middle;
				comparisons++;
			}
		}
		bs.setComparisons(comparisons);
		return bs;
			
	}

	@Override
	public SearchResults sequentialSearch(Object[] arg0, Object arg1){
	SearchResults ss=new SearchResults();
	int comparisons=0;
	ss.setElementFound(false);
	ss.setDataStructureSize(arg0.length);
	boolean found =false;
	for(int i=0; i<arg0.length&&found==false;i++){
		if(arg0[i]==null)
			throw new IllegalArgumentException("Null Elements");
		
		if(arg0[i].equals(arg1)){
			found=true;
			ss.setDataStructureIndex(i);
			ss.setElementFound(true);
			comparisons++;
		}
		else {
			comparisons++;
			
		}
	}
	ss.setComparisons(comparisons);
	return ss;

	}

	
}
