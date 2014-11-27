package edu.cb.scrambler.jenks.ted;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class WordScramblerRunner {

	public static void main(String[] args) {
		WordScrambler ws = new WordScrambler();
		out.println("Test scrambleWord");
		out.println(ws.scrambleWord("TAN"));
		out.println(ws.scrambleWord("ABRACADABRA"));
		out.println(ws.scrambleWord("WHOA"));
		out.println(ws.scrambleWord("AARDVARK"));
		out.println(ws.scrambleWord("EGGS"));
		out.println(ws.scrambleWord("A"));
		out.println(ws.scrambleWord(""));
		out.println("End test scrambleWord");
		out.println("Test scrambleOrRemove");
		List<String> wordList = new ArrayList<String>();
		wordList.add("TAN");
		wordList.add("ABRACADABRA");
		wordList.add("WHOA");
		wordList.add("APPLE");
		wordList.add("EGGS");
		ws.scrambleOrRemove(wordList);
		out.println(wordList);
		out.println("End test scrambleOrRemove");
	}

}
