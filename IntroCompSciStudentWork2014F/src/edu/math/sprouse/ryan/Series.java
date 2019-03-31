package edu.math.sprouse.ryan;
import edu.jenks.dist.math.*;

/**
 * Write a description of class Series here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Series extends AbstractSeries
{
    // this function takes firstnumber and adds commondifference to it until it equals lastterm. everytime commondifference is added to firstterm, add that number to sum
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        int start = firstTerm;
        int end = lastTerm;
        int difference = commonDifference;
        int sum = firstTerm;
        while(start != end){
            start += difference;
            sum += start;
        }
        return sum;
    }
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        double start = firstTerm;
        double end = lastTerm;
        double difference = commonRatio;
        double sum = firstTerm;
        while(equalsRelative(sum, end, RELATIVE_DELTA) == false){
            start = start * difference;
            sum += start;
        }
        return sum;
    }
    public static boolean equalsRelative(double d1, double d2, double delta){
        if(d2 < 0){
            d1 = (d1 + (d2 * -1));
            d2 = (d2 * -1);
        }
        if(d1 < 0){
            d1 = (d2 + (d1 * -1));
            d2 = (d1 * -1);
        }
        if((d2 - d1) <= delta){
            return true;
        }else{
            return false;
        }
    }
}
