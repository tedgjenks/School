package edu.jenks.dist.cb.scrambler;

import java.util.List;

/**
 * @author Ted
 *
 */
public interface Scrambler {
	
	
	/**
	 * <p>Scrambles a given word.</p>
	 * <b>Precondition</b>: <code>word</code> is either an empty string or contains only uppercase letters.<br>
	 * <b>Postcondition</b>: the string returned was created from <code>word</code> as follows:<br>
	 * - the word was scrambled, beginning at the first letter and continuing from left to right<br>
	 * - two consecutive letters consisting of "A" followed by a letter that was not "A" were swapped<br>
	 * - letters were swapped at most once
	 * @param word the word to be scrambled
	 * @return the scrambled word (possibly equal to <code>word</code>)
	 */
	String scrambleWord(String word);
	
	/**
	 * <p>Modifies <code>wordList</code> by replacing each word with its scrambled version, removing any words that are unchanged as a result of scrambling.</p>
	 * <b>Precondition</b>: <code>wordList</code> contains only non-null objects<br>
	 * <b>Postcondition</b>:<br>
	 * - all words unchanged by scrambling have been removed from <code>wordList</code><br>
	 * - each of the remaining words has been replaced by its scrambled version<br>
	 * - the relative ordering of the entries in <code>wordList</code> is the same as it was before the method was called
	 * @param wordList the list of words
	 */
	void scrambleOrRemove(List<String> wordList);

}
