package edu.cb.scrambler.mathis.justin;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler{

	@Override
	public void scrambleOrRemove(List<String> list) {
		for(int i = 0; i < list.size(); i++){
			String temp = list.get(i);
			if(temp.equals(scrambleWord(list.get(i)))){
				list.remove(i);
				i = i - 1;
			}
			else list.set(i, scrambleWord(temp));
		}

	}

	@Override
	public String scrambleWord(String word) {
		String a = "";
		if (word.equals(""))
			return a;
		if (word.length()==1)
			return a+word.charAt(0);
		for(int i = 0; i < word.length()-1; i++){
			
			if (word.charAt(i) == 'A' && word.charAt(i+1) == 'A'){
				a = a+'A';
			}
			else if (word.charAt(i) == 'A'){
				a = a+word.charAt(i+1)+word.charAt(i);
				i = i+1;
			}
			else a = a+word.charAt(i);
			
		}
		if (a.length() >= word.length()){
			a = a.substring(0, word.length()-1);
			return a+word.charAt(word.length()-2);
		}
		return a+word.charAt(word.length()-1);
	}


}
