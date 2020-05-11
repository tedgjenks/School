package edu.cb.wordpair.sweezy.kenneth;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.wordpair.AbstractWordPairList;
import edu.jenks.dist.cb.wordpair.WordPair;

public class WordPairList extends AbstractWordPairList {
	private List<WordPair> wordsT;

	public WordPairList(String[] words) {
		List<WordPair> wordsList = new ArrayList<>();
		for (int i = 0; i < (words.length - 1); i++) {
			for (int j = i + 1; j < (words.length); j++) {
				wordsList.add(new WordPair(words[i], words[j]));
			}
		}
		allPairs = wordsList;
		wordsT = wordsList;
	}

	public static void main(String[] args) {
		WordPairList Testing = new WordPairList(new String[] { "the", "more", "the", "merrier" });
		System.out.println(Testing.wordsT);
	}

	public int numMatches() {
		int counter = 0;
		for (WordPair w : allPairs) {
			if (w.getFirst().equals(w.getLast())) {
				counter++;
			}
		}
		return counter;
	}

}
