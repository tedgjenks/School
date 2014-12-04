package edu.cb.scrambler.guareschi.marco;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;
public class WordScrambler extends java.lang.Object implements Scrambler {

	private static final char TOKEN = 'A';
	public WordScrambler() {
	
	}
	public String scrambleWord(String word){
		StringBuilder sb = new StringBuilder (word.length());
		for(int index = 0; index < word.length() - 1; index++){
			char curChar = word.charAt(index);
			char nextChar  = word.charAt(index + 1);
			if(curChar == TOKEN && nextChar != TOKEN){
				sb.append(nextChar).append(curChar);
				index++;
			} else {
				sb.append(curChar);
			}
			
	if(word.length() > sb.length()){
		sb.append(word.charAt(word.length() - 1));
			}
		}
	return sb.toString();
		
	}

	@Override
	public void scrambleOrRemove(List<String> arg0) {
		
	}
}