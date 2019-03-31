package edu.math.macias.marcus;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries
{
    public static void main(String[] args){
        Series ss = new Series();
        //System.out.println(ss.sumArithmetic(1,16,2));
        System.out.println("My answer: " + ss.sumGeometric(16,1,-.5f));
    }
    public int sumArithmetic(int firstTerm,int lastTerm,int commonDifference){
        int sum = firstTerm;
        int thing = firstTerm + commonDifference;
        if(commonDifference > 0){
            for(int i = (firstTerm + commonDifference) ; i <= lastTerm; i+=commonDifference ){
                sum += i;
                thing += commonDifference;
            }
        }else if(commonDifference < 0){
            for(int i = (firstTerm + commonDifference) ; i >= lastTerm; i+=commonDifference ){
                sum += i;
                thing += commonDifference;
            }
        }
        return sum;
    }
    public static boolean equalsRelative(double d1,double d2,double delta){
        if((d2 - d1) <= delta){
            return true;
        }
        return false;
    }
    public double sumGeometric(float firstTerm,float lastTerm,float commonRatio){
        double sum = firstTerm;
        float i = firstTerm;
        if(commonRatio < 0 && firstTerm > lastTerm){
            while(i != lastTerm){
                i *= commonRatio;
                sum += i;
            }
            double sum2 = (double)sum;
            return sum2;
        }
        if(firstTerm < lastTerm){
            while(true){
                i *= commonRatio;
                sum += i;
                //System.out.println(i);
                if(i >= lastTerm){
                    break;
                }
            }
            double sum2 = (double)sum;
            return sum2;
        }
        if(firstTerm > lastTerm){
            while(true){
                i *= commonRatio;
                sum += i;
                //System.out.println(i);
                if(i <= lastTerm){
                    break;
                }
            }
            double sum2 = (double)sum;
            return sum2;
        }
        
        //System.out.println("Actual Answer: " + test);
        //System.out.println(sum);
        double sum2 = (double)sum;
        return sum2;
    }
}
