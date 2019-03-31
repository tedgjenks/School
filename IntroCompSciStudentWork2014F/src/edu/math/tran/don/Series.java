package edu.math.tran.don;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries
{
   public static void main (String[] args) {
       Series pc = new Series();
       double act = pc.sumGeometric(128, 1 , (float)0.5);
       int whatever = 6;
       for(int i = 0; i < 30; i++) {
           int mult = -3;
           whatever *= mult;
           //System.out.println(whatever);
        }
       System.out.println(act);
   }
   public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
       int sum = 0;
       while(firstTerm != lastTerm) {
           sum += firstTerm;
           firstTerm += commonDifference;
       }
       return sum + lastTerm;
   }
   public static boolean equalsRelative(double d1,double d2,double delta) {
       double difference = Math.abs(d1 - d2);
       if(difference <= delta) {
           return true;
       }
       return false;
   }
   public double sumGeometric(float firstTerm,float lastTerm, float commonRatio) {
       double sum = 0;
       double RELATIVE_DELTA = 0.0010000000474974513;
       boolean works = false;
       if(firstTerm == lastTerm) {
           return firstTerm;
       }
       while(works == false) {
           sum += firstTerm;
           firstTerm *= commonRatio;
           works = equalsRelative(firstTerm, lastTerm, RELATIVE_DELTA);
       }
       return sum + lastTerm;
       /*
       if(firstTerm < lastTerm) {
           double addNum = firstTerm;
           while(Math.abs(addNum) <= lastTerm) {
               //System.out.println(addNum + " fdfa");
               sum += addNum;
               addNum *= commonRatio;
               //System.out.println(addNum);
               //System.out.println(sum + " thgs sum"); 
           }
       }
       if(firstTerm > lastTerm){
           double addNum = firstTerm;
           //System.out.println(Math.abs(addNum));
           //System.out.println(Math.abs(lastTerm));
           if(commonRatio < 0 && Math.abs(firstTerm) < Math.abs(lastTerm)) {
               while(true) {
                   System.out.println("Loop Starts");
                   sum += addNum;
                   addNum *= commonRatio;
                   //System.out.println(addNum + "number added");
                   //System.out.println(sum + " the sum" );
                   if(Math.abs(addNum) > Math.abs(lastTerm) && commonRatio < 0) {
                       break;
                    }
               }
               return sum;
           }
           if(commonRatio < 0) {
               while(addNum != lastTerm) {
                   sum += addNum;
                   addNum *= commonRatio;
               }
               return sum + lastTerm;
               
           }
           
           while(true) {
               System.out.println("Loop Starts");
               sum += addNum;
               addNum *= commonRatio;
               System.out.println(addNum + "number added");
               System.out.println(sum + " the sum" );
               if(Math.abs(addNum) > Math.abs(lastTerm) && commonRatio < 0) {
                   break;
               }
           }
           
       }
       */
       
       
   }
}
