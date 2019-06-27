package edu.math.sprouse.ryan;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational
{
    public Rational(long numerator, long denominator) 
    {
        super(numerator, denominator);
        if(this.getDenominator() < 0){
            setDenominator(this.getDenominator() * -1);
            setNumerator(this.getNumerator() * -1);
        }
        reduce();
    }
    public static void main(String[] args) 
    {
    
    }
    public AbstractRational add(AbstractRational op2)
    {
        long numerator1 = this.getNumerator();
        long denominator1 = this.getDenominator();
        long denominator2 = op2.getDenominator();
        long numerator2 = op2.getNumerator();
        long numeratorSum = (numerator1 * denominator2) + (numerator2 * denominator1);
        long denominatorProduct = denominator1 * denominator2;
        return new Rational(numeratorSum, denominatorProduct);
    }
    public int compareTo(AbstractRational arg0)
    {
        // a negative integer, zero, or a positive 
        //integer as this object is less than, equal to, or greater than the specified object.
        long argNumerator = arg0.getNumerator();
        long argDenominator = arg0.getDenominator();
        long denominator = this.getDenominator();
        long numerator = this.getNumerator();
        long argFraction = argNumerator / argDenominator;
        long fraction = numerator / denominator;
        // fixes fractions if the denominator is negative
        
        if(fraction > argFraction){
            return 1;
        }
        if(fraction == argFraction){
            return 0;
        }
        if(fraction < argFraction){
            return -1;
        }else{
            return 0;
        }
    }
    public boolean decimalTerminates() 
    {
        long denom = this.getDenominator();
        while(denom % 2 == 0){
            denom = denom / 2;
        }
        while(denom % 5 == 0){
            denom = denom / 5;
        }
        if(denom == 1){
            return true;
        }else{
            return false;
        }
    }
    public AbstractRational divide(AbstractRational op2)
    {
        return multiply(op2.reciprocal());
    }
    public boolean equals(AbstractRational op2)
    {
        if((this.getNumerator() == op2.getNumerator()) && (this.getDenominator() == op2.getDenominator())){
            return true;
        }else{
            return false;
        }
    }
    public AbstractRational multiply(AbstractRational op2)
    {
        long numerator1 = this.getNumerator();
        long denominator1 = this.getDenominator();
        long numerator2 = op2.getNumerator();
        long denominator2 = op2.getDenominator();
        long numeratorProduct = numerator1 * numerator2;
        long denominatorProduct = denominator1 * denominator2;
        return new Rational(numeratorProduct, denominatorProduct);
    }
    public AbstractRational reciprocal()
    {
        return new Rational(this.getDenominator(), this.getNumerator());
    }
    public void reduce()
    {
       long numerator = this.getNumerator();
       long denominator = this.getDenominator();
       long currentGCD = 1;
       if(numerator != 0){
           currentGCD = gcd(numerator, denominator);
           while(currentGCD != 1){
               numerator = numerator / currentGCD;
               denominator = denominator / currentGCD;
               setNumerator(numerator);
               setDenominator(denominator);
               currentGCD =  gcd(numerator, denominator);
           }
       }
    }
    public AbstractRational subtract(AbstractRational op2)
    {
        op2.setNumerator(op2.getNumerator() * -1);
        return add(op2);
    }
    public String toString()
    {
        String fraction = "";
        if((this.getDenominator() == 1) || (this.getNumerator() == 0)){
            fraction = "" + this.getNumerator();
        }else{
            fraction = this.getNumerator() + "/" + this.getDenominator();
        }
        return fraction;
    }
}
