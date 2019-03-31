package edu.math.whitt.rose;

import edu.jenks.dist.math.*;
/**
 * Write a description of class Series here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Series extends AbstractSeries
{
    public static void main (String[] args) {
        Series pc = new Series();
        
        
        
        int expectedArith = 80;
        int actualArith = pc.sumArithmetic(80, 80, -700);
        System.out.println(actualArith);
        if (expectedArith == actualArith) {
            //System.out.println(1);
        } else {
            //System.out.println(0);
        }
        
        
        double expectedGeo = 31.0;
        double actualGeo = pc.sumGeometric(1, 16, 2);
        System.out.println(actualGeo);
        if (expectedGeo == actualGeo) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
        int count = firstTerm;
        int sum = firstTerm;
        while (count != lastTerm) {
            count += commonDifference;
            sum += count;
        }
        //return count;
        return sum;
    }
    public double sumGeometric(float firstTerm,  float lastTerm, float commonRatio) {
        double count = firstTerm;
        double sum = firstTerm;
        while (!equalsRelative(count, lastTerm, RELATIVE_DELTA)) {
            count *= commonRatio;
            sum += count;
        }
        //return count;
        return sum;
    }

}
