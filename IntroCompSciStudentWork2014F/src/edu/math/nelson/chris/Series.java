/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.math.nelson.chris;
import edu.jenks.dist.math.*;
/**
 *
 * @author chris
 */
public class Series extends AbstractSeries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Series test = new Series();
        float first = 8;
        float last = (float) .03125;
        float common = (float) .25; 
        System.out.println(test.sumGeometric(first, last, common));
    }
    public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference){
        int term = firstTerm;
        int sum = 0;
        
        while(term!=lastTerm){
            sum += term;
            term += commonDifference;  
        }
        sum+=lastTerm;
        return sum; 
    }
    public double sumGeometric(float firstTerm, float lastTerm, float commonRatio){
        float term = firstTerm;
        float sum = 0;
        float difference = Math.abs(lastTerm - term);
        
        while(difference>.0001){
            
            sum += term;
            
            term *= commonRatio;
            difference = Math.abs(lastTerm - term);
        }
        sum += lastTerm; 
        return sum;
    }
}
