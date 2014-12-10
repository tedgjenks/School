package edu.cb.scrambler.grenci.david;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	private static final char TOKEN = 'A';

	public WordScrambler() {

	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		List<String> nList = new ArrayList<String>();
		for (int i = nList.size() -1; i >= 0 ; i--) {
			String currWord = wordList.get(i);
			String scramWord = scrambleWord(currWord);
			if(currWord.equals(scramWord)) {
				nList.remove(currWord);
				//nList.add(scramWord);
			}
			else {
				nList.set(i, scramWord);
			}
		}
	}
	@Override
	public String scrambleWord(String word) {
		StringBuilder sb =  new StringBuilder(word.length());
		int index = 0;
		for(; index < word.length(); index++) {
			char curChar = word.charAt(index);
			char nexChar = word.charAt(index + 1);
			if(curChar == TOKEN && nexChar != TOKEN){
				sb.append(nexChar).append(curChar);
				index++;
			}
			else {
				sb.append(curChar);
			}
		}
		if(word.length() > sb.length())
			sb.append(word.charAt(word.length() -1));
		return sb.toString();
	}

}
