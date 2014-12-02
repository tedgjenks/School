package edu.cb.scrambler.scates.collin;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler(String word) {
		/// TODO Auto-generated method stub
	}

	@Override
	public void scrambleOrRemove(List<String> list) {
		for(int index = 0; index < list.size(); index++) {
			String curString = list.get(index);
			String newString = scrambleWord(curString);
			list.set(index, newString);
		}
	}

	@Override
	public String scrambleWord(String word) {
		String retVal = new String(word);
		if(word != null && word.length() > 0); {
			retVal = word.substring(0, word.length() - 1);
		}
		return retVal;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
