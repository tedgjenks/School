package edu.cb.wordpair.macias.marcus;

import edu.jenks.dist.cb.wordpair.AbstractWordPairList;
import edu.jenks.dist.cb.wordpair.WordPair;

import java.util.*;
public class WordPairList extends AbstractWordPairList{
	//List<WordPair> allPairs;
	public WordPairList(String[] words) {
		allPairs = new ArrayList<WordPair>();
		for(int i = 0 ; i < words.length - 1;i++) {
			for(int x = i + 1 ; x < words.length;x++) {
				allPairs.add(new WordPair(words[i],words[x]));
			}
		}
	}
	public static void main(String[] args) {
		String[] words = {"one","two","three"};
		String[] words1 = {"the", "more", "the", "merrier"};
		String[] words2 = {"the", "red", "fox", "the","red"};
		String[] words3 = {"a", "a", "a", "a","a"};
		String[] words4 = {};
		String[] words5 = {"",""};
		WordPairList run = new WordPairList(words);
		System.out.println(run.numMatches());
		run = new WordPairList(words1);
		System.out.println(run.numMatches());
		run = new WordPairList(words2);
		System.out.println(run.numMatches());
		run = new WordPairList(words3);
		System.out.println(run.numMatches());
		run = new WordPairList(words4);
		System.out.println(run.numMatches());
		run = new WordPairList(words5);
		System.out.println(run.numMatches());
		
		
	}
	public void test() {
		for(WordPair a : allPairs) {
			System.out.println("(\"" + a.getFirst() + "\", \"" + a.getLast() + "\")  ");
		}
	}
	public int numMatches() {
		int count = 0;
		for(WordPair a : allPairs) {
			if((a.getFirst() != null && a.getLast() != null) && a.getFirst().equals(a.getLast())) {
				count++;
			}
		}
		return count;
	}

}
