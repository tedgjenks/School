/**
 * 
 */
package edu.cb.scrambler.jenks.ted;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

/**
 * @author Ted
 *
 */
public class WordScrambler implements Scrambler {
	
	private final static char TOKEN = 'A';

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.scrambler.Scrambler#scrambleOrRemove(java.util.List)
	 */
	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int index = wordList.size() - 1; index >= 0; index--) {
			String word = wordList.get(index);
			String scrambled = scrambleWord(word);
			if(word.equals(scrambled))
				wordList.remove(index);
			else
				wordList.set(index, scrambled);
		}
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.scrambler.Scrambler#scrambleWord(java.lang.String)
	 */
	@Override
	public String scrambleWord(String word) {
		int wordLength = word.length();
		StringBuilder result = new StringBuilder(wordLength);
		int index = 0;
		for(; index < wordLength - 1; index++) {
			char curChar = word.charAt(index), nextChar = word.charAt(index + 1);
			if(curChar == TOKEN && nextChar != TOKEN) {
				result.append(nextChar);
				index++;
			}
			result.append(curChar);
		}
		if(index < wordLength)
			result.append(word.substring(index));
		return result.toString();
	}

}
