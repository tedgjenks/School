package edu.barrons.creswell.jasmine;
import edu.jenks.dist.barrons.*;
import java.util.*;

 


/**
 * Write a description of class Sentence here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sentence extends AbstractSentence 
{
    public static void main(String[] args) {
        Sentence test = new Sentence("test test test");
        System.out.println(test.countWords());
        test.getWords();
    }
     
    
    
    
    public Sentence() {
        super();
    }
    public Sentence(String sentence) {
        super(sentence);
        
    }
    public List<Integer> getBlankPositions() {
        List <Integer> blanks = new ArrayList<>();
        for (int i =0; i< sentence.length(); i++) {
            String tempyBoii = sentence.substring(i,i+1);
            if (tempyBoii.equals(" ")) {
                blanks.add(i);
            }
        }   
        return blanks;
    }
    public int countWords() {
        int count=1;
        for (int i =0; i< sentence.length(); i++) {
            String tempyBoii = sentence.substring(i,i+1);
            if (tempyBoii.equals(" ")) {
                count++;
            }
        }   
        return count;
    }  
    public String[] getWords() {
        List <String> words = new ArrayList<>();
        int word=0;
        int space=0;
        for (int i =0; i< sentence.length(); i++) {
            String tempyBoii = sentence.substring(i,i+1);
            if (!tempyBoii.equals(" ")) {
                word++;
            } else {
                words.add(sentence.substring(space,space+word));
                space=i+1;
                word=0;
            }
            
        } 
        words.add(sentence.substring(space));
        return words.toArray(new String[words.size()]);
        
        
    }
    
    
}
