package edu.math.britt.emory;
import edu.jenks.dist.math.*; 


public class Series extends AbstractSeries {
    public static void main(String[] args){
        System.out.println("Begin");
        Series s = new Series();
        int actual = s.sumArithmetic(0, 10, 2); 
        int expected = 30; 
        assert actual == expected : "sumArithmetic expected: " + expected + "; actual: " + actual; 
        double act = s.sumGeometric(1, 16, 2);
        double exp = 31.0; 
        assert act == exp : "sumGeometric expected: " + exp + "; actual: " + act;
        //float firstTerm = 243; 
        //float lastTerm = 0.1111111119389534;
        //float ratio = 0.3333333432674408;
        //double act2 = s.sumGeometric(firstTerm, lastTerm, ratio);
        //double exp2 = 31.0; 
        //assert act2 == exp2 : "sumGeometric expected: " + exp2 + "; actual: " + act2;
        System.out.println("End Without Error");
    }
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        int num = firstTerm; 
        int sum = 0; 
        while(num != lastTerm){
            sum += num; 
            num += commonDifference; 
        }
        sum += lastTerm; 
        return sum; 
    }
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        double num = firstTerm; 
        double sum = 0.0; 
        while(equalsRelative(num, lastTerm, RELATIVE_DELTA) == false){
            sum += num; 
            num *= commonRatio; 
        }
        sum += lastTerm;
        return sum; 
    }
}
