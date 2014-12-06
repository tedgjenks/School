package edu.cb.scrambler.li.zhilin;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	private static final char TOKEN = 'A';

	public WordScrambler(String s) {
		String retVal = new String(s);
		if (s != null && s.length() > 0) {
			retVal = s.substring(0, s.length() - 1);
		}
	}

	@Override
	public void scrambleOrRemove(List<String> word) {

	}

	@Override
	public String scrambleWord(String word) {
		StringBuilder sb = new StringBuilder(word.length());
		for(int index = 0; index < word.length() - 1; index++){
			char curChar = word.charAt(index);
			char nextChar = word.charAt(index + 1);
			if(curChar == TOKEN && nextChar != TOKEN) {
				sb.append(nextChar).append(curChar);
				index++;
			}else{
				sb.append(curChar);
			}
		}
		if(word.length() > sb.length())
			sb.append(word.charAt(word.length() - 1));
		return sb.toString();
	}

}