package edu.cb.scrambler.wicker.marshall;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		String scrambled;
		for (int i = 0; i < wordList.size() ; i++){
			scrambled = this.scrambleWord(wordList.get(i));
			if (scrambled.equals(wordList.get(i))){
				wordList.remove(i);
				i--;
			}
			else{
				wordList.set(i, scrambled);
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		char[] c = word.toCharArray();
		for (int i = 0 ; i < c.length ; i++){
			if (c[i] == 'A'){
				if (i + 1 < c.length){
					if (c[i + 1] != 'A'){
						c[i] = c[i + 1];
						c[i + 1] = 'A';
						i++;
					}
				}
			}
		}
		return new String(c);
	}

}
