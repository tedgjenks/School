package edu.cb.scrambler.deloach.hunter;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler{
	private static final char TOKEN='A';

	public WordScrambler() {
	}
	@Override
	public String scrambleWord(String word) {
		StringBuilder sb=new StringBuilder(word.length());
		for (int index=0; index<word.length()-1; index++){
			char curChar=word.charAt(index);
			char nextcurChar=word.charAt(index);
			if(curChar==TOKEN && nextcurChar!=TOKEN){
				sb.append(nextcurChar).append(curChar);
				index++;
			} else{
				sb.append(curChar);
			}
		}
		if (word.length() > sb.length())
			sb.append(word.charAt(word.length()-1));
		return sb.toString();
	}




	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for (int i=0; i < wordList.size(); i++){
			if (wordList.get(i).equals(scrambleWord(wordList.get(i)))){
				wordList.remove(i);
				i+=1;
			}
			else{
				wordList.set(i,  scrambleWord(wordList.get(i)));
			}
		}
		
}
}