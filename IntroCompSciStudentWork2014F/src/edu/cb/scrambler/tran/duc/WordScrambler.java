package edu.cb.scrambler.tran.duc;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		
	}
	
	@Override
	public String scrambleWord(String word) {
		String switchindex = "A";
		StringBuffer t = new StringBuffer();
		String[] wordsplit = word.split("");
		for (int i = 0; i < wordsplit.length - 1; i++){	
				if (wordsplit[i].equals(switchindex) && !wordsplit[i+1].equals(switchindex)){
					t.append(wordsplit[i+1]);
					t.append(wordsplit[i]);
					i++;
				}else{
					t.append(wordsplit[i]);
				}
		}
		if (wordsplit.length != t.length()){
			t.append(wordsplit[wordsplit.length-1]);
		}
		return (t.toString());
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		String scrambledword = null;
		int count = 0;
		for (int i = 0; i < wordlist.size(); i++){
			scrambledword = scrambleWord(wordlist.get(i));
			if (scrambledword.equals(wordlist.get(i))){
				wordlist.set(i, "");
				count++;
			}else{
				wordlist.set(i, scrambledword);
			}
		}
		for (;count >= 0; count--){
			wordlist.remove("");
		}
	}

	public static void main(String[] arg0){
		List<String> list = new ArrayList<String>();
		list.add("TAN");
		list.add("ABRACADABRA");
		list.add("WHOA");
		list.add("APPLE");
		list.add("EGGS");
		list.add("BANANA");
		list.add("AARON");
		list.add("ANTEATER");
		WordScrambler phrase = new WordScrambler();
		phrase.scrambleOrRemove(list);
		System.out.println(list);
	}
}
