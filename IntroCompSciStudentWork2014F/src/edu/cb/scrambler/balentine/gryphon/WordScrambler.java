package edu.cb.scrambler.balentine.gryphon;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler(){
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int index=wordList.size()-1; index>=0; index--){
			String word=wordList.get(index);
			String scrambledWord=scrambleWord(word);
			if(scrambledWord.equals(word))
				wordList.remove(index);
			else{
				wordList.set(index, scrambledWord);
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		String newString="";
		for(int index=0; index<word.length()-1; index++){
			String a="A";
			char charA=word.charAt(index);
			String letterA=Character.toString(charA);
			char charB=word.charAt(index+1);
			String letterB=Character.toString(charB);
			if(letterA.equals(a) && !letterB.equals(a)){
				newString=newString+letterB+a;
				index++;
			}
			else if(letterA.equals(a) && letterB.equals(a)){
				newString=a+newString;
			}
			else if(!letterA.equals(a))
				newString=newString+letterA;
		}
		if(word.length()>newString.length()){
			char lastLetter=word.charAt(word.length()-1);
			newString+=lastLetter;
		}
		return newString;
	}

	public static void main(String[] args) {
	}

}
