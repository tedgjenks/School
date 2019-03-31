package edu.barrons.salter.bella;
import java.util.*;

import edu.jenks.dist.barrons.*;

public class Sentence extends AbstractSentence
{
    public static void main(String[] args) {
        //Sentence[] tester = new Sentence("This is a test");
        
    }
    public Sentence()
    {
        
    }
    public Sentence(String[] sentence) {
        
    }
    public List<Integer> getBlankPositions() {
        ArrayList<Integer> karlMarxsHouseOfFun = new ArrayList<>();
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.substring(i, i+1).equals(" ")) {
                karlMarxsHouseOfFun.add(i);
            }
        }
        return karlMarxsHouseOfFun;
    }
    public int countWords() {
        ArrayList<Integer> arrayPositions = new ArrayList<>();
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.substring(i, i+1).equals(" ")) {
                arrayPositions.add(i);
            }
        }
        int numSpaces = arrayPositions.size();
        int numWords = numSpaces + 1;
        return numWords;
    }
    public String[] getWords() {
        int firstIndOfWord = 0;
        int numWords = countWords();
        ArrayList<Integer> blankPositions = new ArrayList<>();
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.substring(i, i+1).equals(" ")) {
                blankPositions.add(i);
            }
        }
        int lastIndOfWord = 0;
        int prevLastInd = 0;
        String[] words = new String[numWords];
        if(blankPositions.size() == 0) {
            words[0]= sentence.substring(0, sentence.length());
            return words;
        }
        words[0] = sentence.substring(0, blankPositions.get(0));
        firstIndOfWord = blankPositions.get(0) + 1;
        for(int c = 1; c < numWords - 1; c++) {
            lastIndOfWord = blankPositions.get(c);
            words[c] = sentence.substring(firstIndOfWord, lastIndOfWord);
            firstIndOfWord = lastIndOfWord + 1;
        }
        words[numWords - 1] = sentence.substring(firstIndOfWord, sentence.length());
        return words;
    }
}
