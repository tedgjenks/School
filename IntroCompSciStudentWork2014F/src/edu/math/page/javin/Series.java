package edu.math.page.javin;

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
        System.out.println("Start");
        Series instance = new Series();
        System.out.println(instance.sumArithmetic(132, 20, -7));
        System.out.println(instance.sumGeometric(16.0f, 1.0f, -0.5f));
        System.out.println(instance.sumGeometric(243.0f, 0.1111111119389534f, 0.3333333432674408f));
        System.out.println("End without error");
    }
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        int sum = 0;
        while(firstTerm != lastTerm){
            sum += firstTerm;
            firstTerm += commonDifference;
        }
        return sum + lastTerm;
    }
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        double sum = firstTerm;
        if(firstTerm % 1 == 0 && lastTerm % 1 == 0){
            while(firstTerm != lastTerm){
                firstTerm *= commonRatio;
                sum += firstTerm;
                System.out.println(sum);
            }
        }else{
            if(firstTerm > lastTerm){
                while(firstTerm > lastTerm){
                    firstTerm *= commonRatio;
                    sum += firstTerm;
                    System.out.println(sum);
                }
            }else if (firstTerm < lastTerm) {
                while(firstTerm < lastTerm){
                    firstTerm *= commonRatio;
                    sum += firstTerm;
                    System.out.println(sum);
                }
            }
        }
        
        
        return sum;
    }  
}
