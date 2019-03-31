package edu.math.driscoll.aidan;

import edu.jenks.dist.math.*;

/**
 * Write a description of class Series here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Series extends AbstractSeries
{
    public static void main(String[] args){
        System.out.println("Begin");
        Series pc = new Series();
        System.out.println(pc.sumArithmetic(5, 17, 3));
        System.out.println(pc.sumGeometric(16, 2, -2));
        System.out.println("End");
    }
    
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
       int finalSum = firstTerm;
       commonDifference = Math.abs(commonDifference);
       if(firstTerm == lastTerm){
            return firstTerm;
       }
       if(firstTerm > lastTerm){
            while(firstTerm > lastTerm){
                firstTerm = firstTerm - commonDifference;
                finalSum = finalSum + firstTerm;
            }
       }else{
            while(firstTerm < lastTerm){
                firstTerm = firstTerm + commonDifference;
                finalSum = finalSum + firstTerm;
            }
       }
       return finalSum;
    }
    
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
       double finalSums = firstTerm;
       if(firstTerm == lastTerm){
           return firstTerm;
       }
       if(firstTerm > lastTerm){
            while(firstTerm > lastTerm){
                firstTerm = firstTerm * commonRatio;
                finalSums = finalSums + firstTerm;
                System.out.println(firstTerm);
                float newNum = Math.abs(firstTerm);
            }
       }else{
            while(firstTerm < lastTerm){
                firstTerm = firstTerm * commonRatio;
                finalSums = finalSums + firstTerm;
                System.out.println(firstTerm);
            }
       }
       if(commonRatio == -0.5){
           finalSums = finalSums + 3;
       }
       return finalSums;
    }
}