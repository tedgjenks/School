package edu.cb.scrambler.ramsey.will;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	@Override
	public void scrambleOrRemove(List<String> arg0) {
		for(int index = 0; index < arg0.size(); index++){
			if(scrambleWord(arg0.get(index)).equals(arg0.get(index))){
				arg0.remove(index);
			index--;
			}
			else 
				arg0.set(index, scrambleWord(arg0.get(index)));
			}
	}

	@Override
	public String scrambleWord(String arg0) {
		StringBuffer build = new StringBuffer();
		char a = 'A';
		for(int count = 0; count < arg0.length() ;count++){
			if(arg0.charAt(count) == a &&!(count + 1 >= arg0.length()) && arg0.charAt(count+1) != a){
				build.append(arg0.charAt(count+1));
				build.append(arg0.charAt(count));
				count++;
			}
			else
				build.append(arg0.charAt(count));
		}
		String scrambled = build.toString();
		return scrambled;
	}

}
