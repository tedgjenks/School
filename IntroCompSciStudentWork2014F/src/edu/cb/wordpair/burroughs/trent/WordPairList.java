package edu.cb.wordpair.burroughs.trent;
import edu.jenks.dist.cb.wordpair.*;
import java.util.*;

public class WordPairList extends AbstractWordPairList
{
    
    public WordPairList(String[] words){
        super();
        allPairs = new ArrayList<WordPair>(); 
        for(int matchingPair = 0; matchingPair < words.length; matchingPair++){
            for(int addingPair = matchingPair; addingPair < words.length; addingPair++){
                if(matchingPair != addingPair){
                    WordPair pair = new WordPair(words[matchingPair], words[addingPair]);
                    allPairs.add(pair);
                }
            }
        }
    }
    
    public int numMatches(){
        int num = 0;
        for(WordPair pair : allPairs)
            if(pair.getFirst().equals(pair.getLast()))
                num++;
        return num;
    }
    
}
