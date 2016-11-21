/**
 * 
 */
package edu.jenks.dist.barrons.wordset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Stores a set of <code>String</code> objects in no particular order and contains no duplicates.</p>
 * Each word is a sequence of capital letters only.
 * @author Ted Jenks
 *
 */
public abstract class AbstractWordSet {
	private Set<String> wordSet = new HashSet<String>(10);

	/**
	 * Initializes set to empty.
	 */
	public AbstractWordSet() {}
	
	/**
	 * @return the number of words in set.
	 */
	public int size() {
		return wordSet.size();
	}
	
	/**
	 * Adds word to set (no duplicates).
	 * @param word the word to be added.
	 */
	public void insert(String word) {
		wordSet.add(word.toUpperCase());
	}
	
	/**
	 * Removes word from set if present, else does nothing.
	 * @param word the word to be removed.
	 */
	public void remove(String word) {
		wordSet.remove(word);
	}
	
	/**
	 * Returns kth word in alphabetical order, where 1 <= k <= size().
	 * @param k position of word to be returned
	 * @return the kth word
	 */
	public String findkth(int k) {
		List<String> list = new ArrayList<String>(wordSet);
		Collections.sort(list);
		return list.get(k - 1);
	}
	
	/**
	 * @param word
	 * @return true if set contains <code>word</code>, false otherwise.
	 */
	public boolean contains(String word) {
		return wordSet.contains(word);
	}
	
	public abstract int countA();
	
	public abstract void removeA();
	
	public abstract AbstractWordSet commonElement(AbstractWordSet set);

}
