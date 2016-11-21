/**
 * 
 */
package edu.barrons.jenks.ted;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.AbstractSentence;

/**
 * @author Ted Jenks
 *
 */
public class Sentence extends AbstractSentence {

	/**
	 * 
	 */
	public Sentence() {}
	
	/**
	 * @param sentence
	 */
	public Sentence(String sentence) {
		super(sentence);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.sentence.AbstractSentence#getBlankPositions()
	 */
	@Override
	public List<Integer> getBlankPositions() {
		List<Integer> blankPositions = new ArrayList<Integer>(5);
		final char BLANK_CHAR = ' ';
		final int LAST_INDEX = sentence.length() - 1;
		int blankIndex = sentence.indexOf(BLANK_CHAR);
		while(blankIndex >= 0) {
			blankPositions.add(blankIndex);
			blankIndex =  blankIndex < LAST_INDEX ? sentence.indexOf(BLANK_CHAR, blankIndex + 1) : -1;
		}
		return blankPositions;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.sentence.AbstractSentence#countWords()
	 */
	@Override
	public int countWords() {
		return getBlankPositions().size() + 1;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.sentence.AbstractSentence#getWords()
	 */
	@Override
	public String[] getWords() {
		int wordCount = countWords();
		String[] words = new String[wordCount];
		List<Integer> blankPositions = getBlankPositions();
		int beginIndex = 0;
		for(int bpIndex = 0, bpSize = blankPositions.size(); bpIndex < bpSize; bpIndex++) {
			int endIndex = blankPositions.get(bpIndex);
			words[bpIndex] = sentence.substring(beginIndex, endIndex);
			beginIndex = endIndex + 1;
		}
		words[words.length - 1] = sentence.substring(beginIndex);
		return words;
	}

}
