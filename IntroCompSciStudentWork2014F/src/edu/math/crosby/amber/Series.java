package edu.math.crosby.amber;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries
{
    public static void main(String[] args){
        System.out.println("Begin");
        
        System.out.println("End without error");
    }
    
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        if(firstTerm<lastTerm){
            while(firstTerm<=lastTerm){
                int add = firstTerm + commonDifference;
                firstTerm+=add;
            }
        }else{
            while(firstTerm>=lastTerm){
                int add = firstTerm + commonDifference;
                firstTerm+=add;
            }
        }
        return firstTerm;
    }
    
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        return 0;
    }
}
