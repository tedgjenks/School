package edu.cb.wordpair.rhodes.maddux;

import edu.jenks.dist.cb.wordpair.*;
import java.util.*;

public class WordPairList extends AbstractWordPairList{

	public static void main(String[] args) {
		WordPairList test = new WordPairList(new String[] {"the", "red", "fox", "the", "red"});
		test.printArr(test.allPairs);
		System.out.println(test.numMatches());
	}
	
	public WordPairList(String[] arr) {
		allPairs = new ArrayList<WordPair>();
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				WordPair curr = new WordPair(arr[i], arr[j]);
				allPairs.add(curr);
			}
		}
	}
	
	public int numMatches() {
		int match = 0;
		for(WordPair w : allPairs) {
			if(w.getFirst().equals(w.getLast())) {
				match++;
			}
		}
		return match;
	}
	
	public void printArr(List<WordPair> arr) {
		for(int i = 0; i < arr.size()-1; i++) {
			System.out.print(arr.get(i).getFirst() + ":" + arr.get(i).getLast() + ", ");
		}
		System.out.print(arr.get(arr.size()-1).getFirst()+ ":" + arr.get(arr.size()-1).getLast());
		System.out.println();
	}
}
