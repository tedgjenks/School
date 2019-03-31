package edu.cb.wordpair.salter.bella;
import edu.jenks.dist.cb.wordpair.*;
import java.util.*;
public class WordPairList extends AbstractWordPairList
{ 
    public WordPairList(String[] words) {
        allPairs = new ArrayList<>();
        for(int i = 0; i < words.length - 1; i++) {
            for(int c = i + 1; c < words.length; c++) {
                WordPair pair = new WordPair(words[i],words[c]);
                allPairs.add(pair);
            }
        }
    }
    public int numMatches() {
        int numMatches = 0;
        for(int i = 0; i < allPairs.size(); i++) {
            if(allPairs.get(i).getFirst().equals(allPairs.get(i).getLast())) {
                numMatches++;
            }
        }
        return numMatches;
    }
}
