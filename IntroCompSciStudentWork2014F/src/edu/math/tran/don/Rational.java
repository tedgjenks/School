package edu.math.tran.don;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational
{
    private int numerator;
    private int denominator;
    public static void main(String[] args) {
        Rational whatever = new Rational(4, 6);
        Rational whatever2 = new Rational(3,6);
        //AbstractRational newRational = whatever.reciprocal();
        //AbstractRational newRational = whatever.multiply(whatever2);
        //AbstractRational newRational = whatever.divide(whatever2);
        //boolean equalOrNot = whatever.equals(whatever2);
        boolean terminate = whatever.decimalTerminates();
        int test = whatever.compareTo(whatever2);
        String rationalString = whatever.toString();
        AbstractRational newRational = whatever.subtract(whatever2);
        //System.out.println(newRational.toString());
        //System.out.println(terminate);
        //AbstractRational newRational = whatever2.add(whatever);
        //System.out.println(whatever);
        //System.out.println(newRational.getNumerator() + "/" + newRational.getDenominator());
        //System.out.println(equalOrNot);
        System.out.println(test);
        System.out.println(rationalString);
    }
    public Rational(long numer, long denom) throws java.lang.IllegalArgumentException{
        super(numer, denom);
        if(denom < 0 && numer < 0) {
            long newDenom = denom * -1;
            long newNum = numer * -1;
            this.setNumerator(newNum);
            this.setDenominator(newDenom);
        }
        if(denom < 0) {
            long newDenom = denom * -1;
            long newNum = numer * -1;
            this.setNumerator(newNum);
            this.setDenominator(newDenom);
        }
    }
    public AbstractRational reciprocal() {
        Rational newRational = new Rational(this.getDenominator(), this.getNumerator());
        newRational.reduce();
        return newRational;
    }
    public AbstractRational add(AbstractRational op2) {
        long newNumerator = (this.getNumerator() * op2.getDenominator()) + (op2.getNumerator() * this.getDenominator());
        long newDenominator = (this.getDenominator() * op2.getDenominator());
        Rational newRational = new Rational(newNumerator, newDenominator);
        if(newRational.getNumerator() != 0) {
            newRational.reduce();
        }
        return newRational;
    }
    public AbstractRational subtract(AbstractRational op2) {
        long newNumerator = (this.getNumerator() * op2.getDenominator()) - (op2.getNumerator() * this.getDenominator());
        long newDenominator = (this.getDenominator() * op2.getDenominator());
        Rational newRational = new Rational(newNumerator, newDenominator);
        if(newRational.getNumerator() != 0) {
            newRational.reduce();
        }
        return newRational;
    }
    public AbstractRational multiply(AbstractRational op2) {
        long newNumerator = this.getNumerator() * op2.getNumerator();
        long newDenominator = this.getDenominator() * op2.getDenominator();
        Rational newRational = new Rational(newNumerator, newDenominator);
        newRational.reduce();
        return newRational;
    } 
    public AbstractRational divide(AbstractRational op2) {
        long newNumerator = this.getNumerator() * op2.getDenominator();
        long newDenominator = this.getDenominator() * op2.getNumerator();
        Rational newRational = new Rational(newNumerator, newDenominator);
        newRational.reduce();
        return newRational;
    }
    public boolean equals(AbstractRational rn2) {
        this.reduce();
        rn2.reduce();
        if(this.getNumerator() == rn2.getNumerator() && this.getDenominator() == rn2.getDenominator()) {
            return true;
        }
        return false;
    }
    public void reduce() {
        if(this.getNumerator() != 0) {
            long gcd = gcd(getNumerator(), getDenominator());
            this.setNumerator(getNumerator()/gcd);
            this.setDenominator(getDenominator()/gcd);
        }
    }
    public String toString() {
        if(this.getNumerator() == 0 || this.getDenominator() == 0) {
            return "0";
        }
        if(this.getNumerator() != 0) {
            this.reduce();
        }
        if(this.getDenominator() == 1) {
            return "" + this.getNumerator();
        }
        return this.getNumerator() + "/" + this.getDenominator();
    }
    public boolean decimalTerminates() {
        this.reduce();
        if(getNumerator() == getDenominator() || getDenominator() == 1) {
            return true;
        }
        long testDenom = getDenominator();
        while(true) {
            if(testDenom % 2 == 0 || testDenom % 5 == 0) {
                    if(testDenom % 2 == 0) {
                        testDenom /= 2;
                    } else {
                        testDenom /= 5;
                    }
                } else {
                    break;
                }

        }
        if(testDenom == 1) {
            return true;
        }
        return false; 
    }
    public int compareTo(AbstractRational arg0) {
        AbstractRational newRatThis = new Rational(this.getNumerator(), this.getDenominator());
        AbstractRational newRatArg0 = new Rational(arg0.getNumerator(), arg0.getDenominator());
        long newNumeratorOne = newRatThis.getNumerator() * newRatArg0.getDenominator();
        long newNumeratorTwo = newRatArg0.getNumerator() * newRatThis.getDenominator();
        if(newNumeratorOne > newNumeratorTwo) {
            return 1;
        }
        if(newNumeratorOne == newNumeratorTwo) {
            return 0;
        }
        return -1;
    }  
}
