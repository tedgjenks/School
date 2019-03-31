package edu.math.savelyev.denis;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries {
    
    public static void main(String[] args) {
        Series instance = new Series();
        System.out.println(instance.sumGeometric(3, 46875, 5));
    }
    
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
        int sumTemp = firstTerm;
        while (firstTerm != lastTerm){
            firstTerm += commonDifference;
            sumTemp += firstTerm;
        }
        return sumTemp;
    }

    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio) {
        double sumTemp = firstTerm;
        while (firstTerm != lastTerm){
            firstTerm *= commonRatio;
            sumTemp += firstTerm;
        }
        return sumTemp;
    }
}
