package edu.cb.wordpair.whitt.rose;

import java.util.ArrayList;

import edu.jenks.dist.cb.wordpair.AbstractWordPairList;
import edu.jenks.dist.cb.wordpair.WordPair;

public class WordPairList extends AbstractWordPairList {

	public static void main(String[] args) {
		String[] arr = new String[]{"the", "red", "fox", "the", "red"}; 
		WordPairList w = new WordPairList(arr);
		for (int i = 0; i < w.allPairs.size(); i++) {
			System.out.println(w.allPairs.get(i));
		}
		System.out.println(w.numMatches());
	}
	public WordPairList(String[] words) {
		allPairs = new ArrayList<WordPair>();
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				allPairs.add(new WordPair(words[i], words[j]));
			}
		}
	}
	@Override
	public int numMatches() {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < allPairs.size(); i++) {
			if (allPairs.get(i).getFirst().contentEquals(allPairs.get(i).getLast())) {
				count++;
			}
		}
		return count;
	}

}
