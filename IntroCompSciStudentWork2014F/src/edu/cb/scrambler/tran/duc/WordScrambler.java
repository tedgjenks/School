package edu.cb.scrambler.tran.duc;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		
	}
	
	@Override
	public String scrambleWord(String word) {
		String split = "A";
		StringBuffer t = new StringBuffer();
		String[] wordsplit = word.split("");
		for (int i = 0; i < wordsplit.length; i++){	
				try{
					if (wordsplit[i].equals(split)){
						t.append(wordsplit[i+1]);
						t.append(wordsplit[i]);
						i++;
					}else{
						t.append(wordsplit[i]);
					}
				}catch (ArrayIndexOutOfBoundsException e){
					t.append(wordsplit[i]);
				}
		}
		return (t.toString());
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		List<String> wordscrambled = new ArrayList<String>();
		List<String> wordremoved = new ArrayList<String>();
		String currentword = null;
		for (int i = 0; i < wordlist.size(); i ++){
			currentword = scrambleWord(wordlist.get(i));
			wordscrambled.add(currentword);
		}
		for (int c = 0; c < wordlist.size(); c++){
			if (wordlist.get(c).equals(wordscrambled.get(c))){
			}else{
				wordremoved.add(wordscrambled.get(c));
			}
		}
	}

}
