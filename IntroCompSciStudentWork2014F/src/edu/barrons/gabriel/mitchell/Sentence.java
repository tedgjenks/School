package edu.barrons.gabriel.mitchell;
import edu.jenks.dist.barrons.*;
import java.util.*;
public class Sentence extends AbstractSentence{
   public Sentence(){
        super();
   }
   public Sentence(String sentence){
        super(sentence);
   }
   public List<Integer> getBlankPositions(){
        ArrayList<Integer> blanks = new ArrayList<Integer>();
        for(int i = 0; i < sentence.length();i++){
            if (sentence.charAt(i) == ' '){
                blanks.add(i);
            }
        }
        return blanks;
   }
   public int countWords(){
        int numBlanks = getBlankPositions().size();
        return numBlanks+1;
   }
   public String[] getWords(){
        String[] words = new String[countWords()];
        int numBlanks = getBlankPositions().size();
        int startInd = 0;
        int i = 0;
        for (int y = 0; y < numBlanks; y++){
            words[i] = (sentence.substring(startInd,getBlankPositions().get(y)));
            i++;
            startInd = getBlankPositions().get(y)+1;
        }
        words[countWords()-1] = (sentence.substring(startInd,sentence.length()));
        return words;
   }
}