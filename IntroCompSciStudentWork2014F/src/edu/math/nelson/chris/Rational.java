/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.math.nelson.chris;
import edu.jenks.dist.math.AbstractRational; 
/**
 *
 * @author chris
 */
public class Rational extends AbstractRational{

    /**
     * @param args the command line arguments
     */
    
    public Rational(long numer, long denom){
        super(numer,denom);

        if(denom<0){
            long newNumer = numer - (2*numer);
            long newDenom = denom + (-2*denom);
            super.setNumerator(newNumer);
            super.setDenominator(newDenom);
        }
        reduce();
    }
    public static void main(String[] args) {
        Rational test = new Rational(789,433);
        Rational num = new Rational (147,313);
        
        System.out.println(num.compareTo(test));
    }
    public boolean equals(AbstractRational rn2){
        long num1 = this.getNumerator();
        long num2 = rn2.getNumerator();
        long den1 = this.getDenominator();
        long den2 = rn2.getDenominator();
        
        return (num1==num2)&&(den1==den2);
    }
    public void reduce(){
        long den = this.getDenominator();
        long num = this.getNumerator();
        long greatest = 1;
        if(num != 0){
            greatest = gcd(num, den);
        }
        num = num / greatest;
        den = den / greatest;
        this.setNumerator(num);
        this.setDenominator(den);
    }
    public String toString(){
        long num = this.getNumerator();
        long den = this.getDenominator();
        if(num % den == 0){
            long integer = num / den;
            return "" + integer; 
        }
        return num + "/" + den;
        
    }
    public boolean decimalTerminates(){
        long denom = this.getDenominator();
        while(denom % 2 ==0 || denom %5 == 0){
            if(denom % 2 == 0){
                denom /= 2;
            }else{
                denom /= 5; 
            }
            System.out.println(denom);
        }
        if(denom == 1){
            return true;
        }
        return false;
    }
    public int compareTo(AbstractRational arg0){
            long num1 = this.getNumerator();
            long num2 = arg0.getNumerator();
            long den1 = this.getDenominator();
            long den2 = arg0.getDenominator();

           num1 *= den2;
           num2 *= den1;
           den1 *= den2;
           Rational difference = new Rational((num1-num2),den1);
           long diff = difference.getNumerator();
           int ret = Math.toIntExact(diff);
          if(diff>0 && diff<Integer.MAX_VALUE){
               return ret;
          }else if(diff<0 && diff>Integer.MIN_VALUE){
              return ret; 
          }
          return 0;
    }
    public AbstractRational multiply(AbstractRational op2){
        long num1 = this.getNumerator();
        long num2 = op2.getNumerator();
        
        long den1 = this.getDenominator();
        long den2 = op2.getDenominator();
        
        long newNum = num1 * num2;
        long newDen = den1 * den2;
        
        Rational mult = new Rational(newNum, newDen);
        return mult; 
    }
    public AbstractRational divide(AbstractRational op2){
       return this.multiply(op2.reciprocal());
    }
    public AbstractRational reciprocal(){
        long num = this.getNumerator();
        long den = this.getDenominator();
        long temp = this.getNumerator();
        num = den;
        den = temp; 
        Rational recip = new Rational(num, den);
        return recip;
    }
    public AbstractRational subtract(AbstractRational op2){
        long negNum = -op2.getNumerator();
        long den = op2.getDenominator();
        Rational newRational = new Rational(negNum,den);
        return this.add(newRational);
    }
    public AbstractRational add(AbstractRational op2){
        long num1 = this.getNumerator();
        long num2 = op2.getNumerator();
        
        long den1 = this.getDenominator();
        long den2 = op2.getDenominator();
        num2 *= den1; 
        num1 *= den2;
        den1 *= den2;

        long newNum = num1 + num2;
  
        Rational add = new Rational(newNum, den1);
        return add; 
    }
}
