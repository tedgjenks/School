package edu.math.crosby.amber;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries
{
    public static void main(String[] args){
        System.out.println("Begin");
        Series test = new Series();
        System.out.println(test.sumArithmetic(132,20,-7));
        System.out.println("End without error");
    }
    
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        if (firstTerm<lastTerm){
            int num = firstTerm;
            while(num<lastTerm){
                num += commonDifference;
                firstTerm += num;
            }
            return firstTerm;
        }else{
            int num = lastTerm;
            while(num<firstTerm){
                num -= commonDifference;
                lastTerm += num;
            }
            return lastTerm;
        }
    }
    
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        if (firstTerm<lastTerm){
            double num = firstTerm;
            while(!equalsRelative(num,lastTerm,RELATIVE_DELTA)){
                num *= commonRatio;
                firstTerm += num;
            }
            return firstTerm;
        }else{
            double num = lastTerm;
            while(!equalsRelative(num,firstTerm,RELATIVE_DELTA)){
                num /= commonRatio;
                lastTerm += num;
            }
            return lastTerm;
        }
    }
}
