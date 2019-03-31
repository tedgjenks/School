package edu.math.hollingsworth.james;

import edu.jenks.dist.math.AbstractSeries;

public class Series extends AbstractSeries {
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
        int result = 0;
        if(firstTerm < lastTerm) for(int i = firstTerm; i <= lastTerm; i += commonDifference) result += i;
        else for(int i = firstTerm; i >= lastTerm; i += commonDifference) result += i;
        return result;
    }

    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio) {
        double result = 0;
        for(double i = firstTerm; !equalsRelative(i, lastTerm, RELATIVE_DELTA); i *= commonRatio) result += i;
        return result + lastTerm;
    }
}
