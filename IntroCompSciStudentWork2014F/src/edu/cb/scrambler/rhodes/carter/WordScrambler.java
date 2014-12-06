package edu.cb.scrambler.rhodes.carter;

import java.util.List; 

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	private static final char TOKEN ='A';
	

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int i = 0; i <=wordList.size()-1; i++){
			if(wordList.get(i).equals(scrambleWord(wordList.get(i)))){
				wordList.remove(i);
			}else{
				wordList.set(i, scrambleWord(wordList.get(i)));
			}
		}	
	}

	@Override
	public String scrambleWord(String word){
		StringBuilder sb = new StringBuilder(word.length());
		for(int i = 0; i < word.length() - 1;i ++){
			char curChar = word.charAt(i);
			char nextChar = word.charAt(i + 1);
			if(curChar == TOKEN && nextChar != TOKEN){
				sb.append(nextChar).append(curChar);
				i++;
			}else{
				sb.append(curChar);
			}
		}
		if(word.length() > sb.length())
			sb.append(word.charAt(word.length() - 1));
		return sb.toString();
	}

}
