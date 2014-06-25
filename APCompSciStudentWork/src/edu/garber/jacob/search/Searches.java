package edu.garber.jacob.search;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;

public class Searches implements Searcher {

	@Override
	public SearchResults binarySearch(Comparable[] arg0, Object arg1)
			throws IllegalArgumentException {
			SearchResults b1 = new SearchResults();
			boolean b = false;
			int c1 = 0;
			int c2 = 0;
			int c3 = arg0.length;
			b1.setDataStructureSize(arg0.length);
			while (b==false && c1<=Math.ceil(Math.log(arg0.length)/Math.log(2))) {
				int name = (c2+c3)/2;
				if (arg0[name]==null) {
					throw new IllegalArgumentException ("There are null elements");
					}
				if (arg0[name].compareTo(arg1)==0) {
					b=true;
					b1.setElementFound(true);
					b1.setDataStructureIndex(name);
					c1++;
				}
				else if (arg0[name].compareTo(arg1)>0) {
					c3=name;
					c1++;
				}
				else if (arg0[name].compareTo(arg1)<0) {
					c2=name;
					c1++;
				}
			}
			b1.setComparisons(c1);
				
		return b1;
	}

	@Override
	public SearchResults sequentialSearch(Object[] arg0, Object arg1)
			throws IllegalArgumentException {
		boolean a = false;
		SearchResults s1 = new SearchResults();
		int comparison = 0;
		s1.setDataStructureSize(arg0.length);
		for(int index=0; index<arg0.length&& a==false; index++) {
			if (arg0[index]==null) {
				throw new IllegalArgumentException ("There are null elements");
			}
			comparison++;
			if (arg0[index].equals(arg1) ){
				a = true;
				s1.setElementFound(true);
				s1.setDataStructureIndex(index);
				
			}
		}
			s1.setComparisons(comparison);
		return s1;
		}
	}


