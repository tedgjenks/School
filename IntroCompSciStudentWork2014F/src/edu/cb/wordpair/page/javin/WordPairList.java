package edu.cb.wordpair.page.javin;
import edu.jenks.dist.cb.wordpair.*;
import java.util.*;
public class WordPairList extends AbstractWordPairList{
	private List<WordPair> allPairs;
	public WordPairList(String[] words) {
		allPairs = new ArrayList<>();
		for(int i = 0; i < words.length-1; i++) {
			for(int j = i+1; j < words.length; i++) {
				if(words[i].equals(words[j])) {
					allPairs.add(new WordPair(words[i], words[j]));
				}
			}
		}
	}
	@Override
	public int numMatches() {
		// TODO Auto-generated method stub
		return allPairs.size();
	}

}
