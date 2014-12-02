package edu.cb.scrambler.gresko.michael;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	
	
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		// TODO Auto-generated method stub

	}

	@Override
	public String scrambleWord(String wordList) {
		String word = "The Scramble is: ";
        char[] wordArray = wordList.toCharArray();
        int i, len = wordList.length();
        for (i = 0; i <= len; i++){
           char temp = wordArray[i];
           if (temp != 'A') {
           word = "" + temp;
           } else {
            i = i + 1;
           	temp = wordArray[i];
           	word = "" + temp;
           	i = i - 1;
           	temp = wordArray[i];
           	word = "" + temp;
           	i = i + 1;
           }
        
        }
		/*ArrayList al = new ArrayList();
		ArrayList aln = new ArrayList();
		al.add(wordList);
		int i, len = al.size();
		for (i = 0; i <= len; i++) {
			if (i != 'A') {
				aln.add(i);
			} else {
				al.set(i, i + 1);
				aln.add(i);
				al.set(i, i - 1);
				aln.add(i);
				i = i + 1;
			}
		}*/
		return /*aln.toString()*/ word;

	}
	

}
