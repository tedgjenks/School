package edu.cb.wordpair.creswell.jasmine;
import edu.jenks.dist.cb.wordpair.*;
import java.util.*; 


public class WordPairList extends AbstractWordPairList
{
   
   public WordPairList(String[] words) {
       super();
       allPairs = new ArrayList<WordPair>(); 
       for(int i=0; i< words.length; i++) {
           for (int s=i+1; s < words.length; s++) {
               WordPair pair = new WordPair(words[i], words[s]);
               allPairs.add(pair);
            }
       }
       
    }
    public int numMatches() {
        int mat =0;
        for (WordPair pair: allPairs) {
            if (pair.getFirst().equals(pair.getLast())) {
                mat++;
            }
        }
        return mat;
    }
}
