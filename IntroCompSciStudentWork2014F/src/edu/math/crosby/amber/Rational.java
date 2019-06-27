package edu.math.crosby.amber;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational
{
    public static void main(String[] args){
        
    }
    
    public Rational(long numer,long denom){
        super(numer, denom);
        if(denom < 0){
            setNumerator(numer-(numer*2));
            setDenominator(denom-(denom*2));
        }
        reduce();
    }
    
    public AbstractRational reciprocal(){
        long numerator = getDenominator();
        long denominator = getNumerator();
        return new Rational(numerator, denominator);
    }
    
    public AbstractRational add(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            long num = getNumerator() + op2.getNumerator();
            long den = getDenominator();
            return new Rational(num, den);
        }else{
            long deno = getDenominator() * op2.getDenominator();
            long nume1 = getNumerator() * op2.getDenominator();
            long nume2 = op2.getNumerator() * getDenominator();
            long nume = nume1 + nume2;
            return new Rational(nume, deno);
        }
    }
    
    public AbstractRational subtract(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            long num = getNumerator() - op2.getNumerator();
            long den = getDenominator();
            return new Rational(num, den);
        }else{
            long deno = getDenominator() * op2.getDenominator();
            long nume1 = getNumerator() * op2.getDenominator();
            long nume2 = op2.getNumerator() * getDenominator();
            long nume = nume1 - nume2;
            return new Rational(nume, deno);
        }
    }
    
    public AbstractRational multiply(AbstractRational op2){
        long num = getNumerator() * op2.getNumerator();
        long den = getDenominator() * op2.getDenominator();
        return new Rational(num, den);
    }
    
    public AbstractRational divide(AbstractRational op2){
        long num = getNumerator() * op2.getDenominator();
        long den = getDenominator() * op2.getNumerator();
        return new Rational(num, den);
    }
    
    public boolean equals(AbstractRational rn2){
        if(getNumerator() == rn2.getNumerator() && getDenominator() == rn2.getDenominator()){
           return true;  
        }else{
           return false;
        }
    }
    
    public void reduce(){
        if(getNumerator() != 0){
            long something = gcd(getNumerator(),getDenominator());
            if (something == 0){
                something = 1;
            }
            setNumerator(getNumerator() / something);
            setDenominator(getDenominator() / something);
        }
    }
    
    public String toString(){
        if (getDenominator() == 1){
            return String.valueOf(getNumerator());
        }else if (getNumerator() == 0){
            return String.valueOf(0);
        }else{
            return getNumerator() + "/" + getDenominator();
        }
    }
    
    public boolean decimalTerminates(){
        long newDen = getDenominator();
        while(newDen % 2 == 0){
            newDen = newDen / 2;
        }
        while(newDen % 5 ==0){
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
            setNumerator(getNumerator()-(getNumerator()*2));
            setDenominator(getDenominator()-(getDenominator()*2));
        }
        reduce();
        if(arg0.getDenominator() < 0){
            arg0.setNumerator(arg0.getNumerator()-(arg0.getNumerator()*2));
            arg0.setDenominator(arg0.getDenominator()-(arg0.getDenominator()*2));
        }
        arg0.reduce();
        long deno = getDenominator() * arg0.getDenominator();
        long nume1 = getNumerator() * arg0.getDenominator();
        long nume2 = arg0.getNumerator() * getDenominator();
        if(nume1 > nume2){
            return 1;
        }
        if(nume1 < nume2){
            return -1;
        }
        return 0;
    }
}
