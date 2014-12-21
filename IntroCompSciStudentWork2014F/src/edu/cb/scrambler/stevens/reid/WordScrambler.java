package edu.cb.scrambler.stevens.reid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler{
	@Override
	public void scrambleOrRemove(List<String> origList) {
		ArrayList<String> finalList = new ArrayList<>(Arrays.asList((String[]) origList.toArray()));
		for (int i = origList.size() - 1 ; i >= 0 ; i--)
			if (origList.get(i).equals(scrambleWord(origList.get(i))))
				finalList.remove(i);
	}

	@Override
	public String scrambleWord(String input) {
		char[] origInput = input.toCharArray();
		for (int i = 0; i <= origInput.length - 2 ; i++){
			if (origInput[i] == 'A' && origInput[i + 1] != 'A'){
				origInput[i] = origInput[i + 1];
				origInput[i + 1] = 'A';
				i++;
			}
		}
		return new String(origInput);
	}
	
}
