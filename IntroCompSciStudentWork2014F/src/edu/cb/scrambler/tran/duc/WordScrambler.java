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
		for (int i = 0; i > wordsplit.length; i ++){
			if (wordsplit[i].equals(split)){
				t.append(wordsplit[i + 1]);
				t.append(wordsplit[i]);
			}else{
				t.append(wordsplit[i]);
			}
		}
		return wordsplit.toString();
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		ArrayList words = new ArrayList();
		String currentword = null;
	}

	

}
