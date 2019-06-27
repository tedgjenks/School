package edu.math.driscoll.aidan;

import edu.jenks.dist.math.*;
/**
 * Write a description of class Rational here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rational extends AbstractRational
{
    public static void main(String[] args){
        System.out.println("Begin");
        AbstractRational whatever = new Rational(-313,-944);
        AbstractRational whatever2 = new Rational(51,25);
        System.out.println(whatever.compareTo(whatever2));
        System.out.println("End");
    }
    
    public Rational(long numer, long denom){
        super(numer, denom);
        if(denom < 0){
            setNumerator(-1 * numer);
            setDenominator(-1 * denom);
        }
        reduce();
    }
    
    public AbstractRational reciprocal(){
        long temp = getNumerator();
        long numerator = getDenominator();
        long denominator = temp;
        return new Rational(numerator, denominator);
    }
    
    public AbstractRational add(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            long numerat = getNumerator() + op2.getNumerator();
            long denomin = getDenominator();
            return new Rational(numerat, denomin);
        }else{
            long tempOne = getNumerator();
            long tempTwo = getDenominator();
            long tempThree = op2.getNumerator();
            long tempFour = op2.getDenominator();
            long commonDenom = tempTwo * tempFour;
            long firstNum = tempOne * tempFour;
            long secondNum = tempTwo * tempThree;
            long finalN = firstNum + secondNum;
            return new Rational(finalN, commonDenom);
        }
    }
    
    public AbstractRational subtract(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            long numerat = getNumerator() - op2.getNumerator();
            long denomin = getDenominator();
            return new Rational(numerat, denomin);
        }else{
            long tempOne = getNumerator();
            long tempTwo = getDenominator();
            long tempThree = op2.getNumerator();
            long tempFour = op2.getDenominator();
            long commonDenom = tempTwo * tempFour;
            long firstNum = tempOne * tempFour;
            long secondNum = tempTwo * tempThree;
            long finalN = firstNum - secondNum;
            return new Rational(finalN, commonDenom);
        }
    }
    
    public AbstractRational multiply(AbstractRational op2){
        long num  = getNumerator() * op2.getNumerator();
        long deno = getDenominator() * op2.getDenominator();
        return new Rational(num, deno);
    }
    
    public AbstractRational divide(AbstractRational op2){
        long numera = getNumerator() * op2.getDenominator();
        long denomi = getDenominator() * op2.getNumerator();
        return new Rational(numera, denomi);
    }
    
    public boolean equals(AbstractRational rn2){
        if(getNumerator() == rn2.getNumerator() && getDenominator() == rn2.getDenominator()){
           return true; 
        }else{
           return false;
        }
    }
    
    public String toString(){
        long denom = getDenominator();
        long numer = getNumerator();
        if(getDenominator() == 1){
            return String.valueOf(getNumerator());
        }else{
            if(getNumerator() == 0){
                return "0";
            }
        }
        return getNumerator() + "/" + getDenominator();
    }
    
    public void reduce(){
        if(getNumerator() == 0){
            
        }else{
            long num = gcd(getNumerator(), getDenominator());
            if(num == 0){
                num = 1;
            }
            setNumerator(getNumerator() / num);
            setDenominator(getDenominator()/ num);
        }
    }
    
    public boolean decimalTerminates(){
        long newDen = getDenominator();
        while(newDen % 2 == 0){
            newDen = newDen / 2;
        }
        while(newDen % 5 == 0){
            newDen = newDen / 5;
        }
        if(newDen == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public int compareTo(AbstractRational arg0){
       if(getDenominator() < 0){
          setNumerator(-1 * getNumerator());
          setDenominator(-1 * getDenominator());
       }
       reduce();
       if(arg0.getDenominator() < 0){
          arg0.setNumerator(-1 * arg0.getNumerator());
          arg0.setDenominator(-1 * arg0.getDenominator());
       }
       arg0.reduce();
       long denom = getDenominator() * arg0.getDenominator();
       long num = getNumerator() * arg0.getDenominator();
       long otherNum = arg0.getNumerator() * getDenominator();
       if(num > otherNum){
           return 1;
       }
       if(num < otherNum){
           return -1;
       }
       return 0;
    }
}

