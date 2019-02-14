package edu.barrons.burroughs.trent;
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
        ArrayList<Integer> numBlanks = new ArrayList<>();
        
        for(int i =0; i < sentence.length(); i++){
            String tempyBoi = sentence.substring(i, i + 1); 
            if(tempyBoi.equals(" ")){
                numBlanks.add(i);
            }
        }
        return numBlanks;
    }
    
    public int countWords(){
        int numCount = 1;
        
        for(int i =0; i < sentence.length(); i++){
            String tempyBoi = sentence.substring(i, i + 1); 
            if(tempyBoi.equals(" ")){
                numCount++;
            }
        }
        return numCount;
    }
    
    public String[] getWords(){
        ArrayList<String> words = new ArrayList<>();
        
        int numWords = countWords();
        int numPlace = 0;
        
        if(numWords == 1){
            String tempyBoi = sentence.substring(0, sentence.length());
            words.add(tempyBoi);
        } else {  
            for(int i = numPlace; i < sentence.length(); i++){
                if(i == sentence.length() - 1){
                    String tempyBoi = sentence.substring(numPlace, sentence.length());
                    words.add(tempyBoi);
                }
                if(sentence.substring(i, i + 1).equals(" ")){
                    String tempyBoi = sentence.substring(numPlace, i);
                    words.add(tempyBoi);
                    numPlace = i + 1;
                }
            }
        }
        return words.toArray(new String[words.size()]);
    }
}
