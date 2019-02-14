/**
 * 
 */
package edu.jenks.dist.cb.wordpair;

import java.util.*;

/**
 * @author Jenks
 *
 */
public abstract class AbstractWordPairList {

	
	/**
	 * The list of word pairs, initialized by the constructor.
	 */
	public List<WordPair> allPairs;
	
	/**
	 * @return the number of <code>WordPair</code> objects in <code>allPairs</code> for which the two strings match.
	 */
	public abstract int numMatches();
}
