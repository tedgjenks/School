/**
 * 
 */
package edu.cb.wordpair.jenks.ted;

import edu.jenks.dist.cb.wordpair.*;
import java.util.*;

/**
 * @author Jenks
 *
 */
public class WordPairList extends AbstractWordPairList {
	
	/**
	 * Initialize <code>allPairs</code> to an <code>ArrayList</code> of <code>WordPair</code> objects.<br>
	 * 
	 * A <code>WordPair</code> object consists of a word from the array paired with a word that appears later in the array.<br>
	 * The <code>allPairs</code> list contains <code>WordPair</code> objects <code>(words[i], words[j])</code> for every <code>i</code> and <code>j</code>,<br>
	 * where <code>i</code> is less than <code>j</code>.  Each <code>WordPair</code> object is added exactly once to the list.
	 * @param words <code>words.length &ge; 2</code>
	 */
	public WordPairList(String[] words) {
		allPairs = new ArrayList<>(determineNumPairs(words.length));
		for(int index = 0; index < words.length - 1; index++) {
			for(int index2 = index + 1; index2 < words.length; index2++)
				allPairs.add(new WordPair(words[index], words[index2]));
		}
	}
	
	private int determineNumPairs(int numWords) {
		int n = numWords - 1;
		return n * (n + 1) / 2;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.wordpair.AbstractWordPairList#numMatches()
	 */
	@Override
	public int numMatches() {
		int matches = 0;
		for(WordPair wp : allPairs) {
			if(wp.getFirst().equals(wp.getLast()))
				matches++;
		}
		return matches;
	}

}
