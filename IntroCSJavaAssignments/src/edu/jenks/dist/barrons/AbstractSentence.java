/**
 * 
 */
package edu.jenks.dist.barrons;

import java.util.List;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractSentence {
	
	/**
	 * 
	 */
	protected String sentence;
	
	/**
	 * <b>Postcondition:</b> <code>sentence</code> is null.
	 */
	public AbstractSentence() {}

	/**
	 * <b>Postcondition:</b> <code>sentence</code> is trimmed.
	 */
	public AbstractSentence(String sentence) {
		this.sentence = sentence.trim();
	}
	
	/**
	 * @return an <code>ArrayList</code> of integer positions (indexes) containing a blank in this sentence. If there are no blanks in the sentence, returns an empty list.
	 */
	public abstract List<Integer> getBlankPositions();
	
	/**
	 * <b>Precondition:</b> Sentence contains at least one word.
	 * @return the number of words in this sentence (spaces separate words).
	 */
	public abstract int countWords();
	
	/**
	 * <b>Precondition:</b><br>
	 * - Any two words in the sentence are separated by one blank.<br>
	 * - The sentence contains at least one word.<br>
	 * <b>Postcondition:</b> String[] returned containing the words in this sentence.
	 * @return
	 */
	public abstract String[] getWords();

	/**
	 * @return
	 */
	public String getSentence() {
		return sentence;
	}

	/**
	 * @param sentence
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
}
