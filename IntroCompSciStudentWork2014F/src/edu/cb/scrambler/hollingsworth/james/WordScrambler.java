/**
 * 
 */
package edu.cb.scrambler.hollingsworth.james;

import java.util.List;
import edu.jenks.dist.cb.Scrambler;

/**
 * @author Ted Jenks
 *
 */
public class WordScrambler implements Scrambler {
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.scrambler.Scrambler#scrambleOrRemove(java.util.List)
	 */
	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int i = 0; i < wordList.size();) {
			if(wordList.get(i).equals(scrambleWord(wordList.get(i)))) wordList.remove(i);
			else {
				wordList.set(i, scrambleWord(wordList.get(i)));
				i++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.scrambler.Scrambler#scrambleWord(java.lang.String)
	 */
	@Override
	public String scrambleWord(String word) {
		if(!word.contains("A")) return word;
		for(int i = 0; i < word.length() - 1; i++) {
			if(word.charAt(i) == 'A' && word.charAt(i + 1) != 'A') {
				word = word.substring(0, i) + word.substring(i + 1, i + 2) + "A" + word.substring(i + 2);
				i++;
			}
		}
		return word;
	}
	
	public static void main(String[] args) {
		WordScrambler w = new WordScrambler();
		System.out.println(w.scrambleWord("APPLE"));
	}

}
