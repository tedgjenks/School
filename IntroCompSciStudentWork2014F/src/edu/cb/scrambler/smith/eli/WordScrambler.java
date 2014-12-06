package edu.cb.scrambler.smith.eli;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	
	private static final String TOKEN = "A";

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for (int count = wordList.size()-1;wordList.size()>=count; count-- ){
			String original = wordList.get(count);
			String scrambled = scrambleWord(original);
			if (original == scrambled){
				wordList.remove(original);
			}
			else{
				wordList.remove(original);
				wordList.add(scrambled);
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		StringBuilder newString = new StringBuilder(word.length()) ;
		for(int count = 0; count < (word.length() -1); count++){
			String current = word.substring(count, count + 1);
			String next = word.substring(count + 1, count + 2);
			if (current.equals(TOKEN) && !next.equals(TOKEN)){
				newString.append(next).append(current);
				count++;
			}
			else{
				newString.append(current);
			}
		
		}
		if (newString.length() < word.length()){
			newString.append(word.substring(word.length()-1));
		}
		
		return newString.toString();
	}
}