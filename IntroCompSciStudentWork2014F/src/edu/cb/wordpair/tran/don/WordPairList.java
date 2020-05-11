package edu.cb.wordpair.tran.don;

import java.util.ArrayList;

import edu.jenks.dist.cb.wordpair.*;

public class WordPairList extends AbstractWordPairList{

	public static void main(String args[]) {
		String[] str = {"the", "red", "fox", "the", "red"};
		WordPairList wp = new WordPairList(str);
		System.out.println(wp.numMatches());
	}
	
	public WordPairList(String[] words) {
		allPairs = new ArrayList<WordPair>();
		for(int i = 0; i < words.length - 1; i++) {
			for(int j = i+1; j < words.length; j++) {
				allPairs.add(new WordPair(words[i], words[j]));
			}
		}
		/*
		for(WordPair wp : allPairs) {
			System.out.println("{" + wp.getFirst() + ", " + wp.getLast() + "}");
		}
		*/
	}
	
	public int numMatches() {
		int count = 0;
		for(WordPair wp : allPairs) {
			if(wp.getFirst().equals(wp.getLast()))
					count++;
		}
		return count;
	}

}
