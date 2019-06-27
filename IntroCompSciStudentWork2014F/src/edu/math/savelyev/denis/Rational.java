package edu.math.savelyev.denis;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational{
    static long numerator = 0;
    static long denominator = 0;
    public static void main(String[] args){
        Rational instance = new Rational(-335, 93);
        Rational instance2 = new Rational(-335, 381);
        System.out.println(instance.compareTo(instance2));
        System.out.println(instance.getNumerator());
        System.out.println(instance2.getNumerator());
    }
    public Rational(long numerator, long denominator) throws IllegalArgumentException {
        super(numerator, denominator);
        if(denominator < 0) {
            setDenominator(Math.abs(denominator));
            setNumerator(numerator *= -1);
        }
    }
    public AbstractRational reciprocal() {
        if (getNumerator() < 0) {
            setNumerator(Math.abs(getNumerator()));
            Rational temp = new Rational(getDenominator() * -1, getNumerator());
            return temp;
        }
        setNumerator(Math.abs(getNumerator()));
        Rational temp = new Rational(getDenominator(), getNumerator());
        return temp;
    }
    public AbstractRational add(AbstractRational op2) {
        Rational var1 = new Rational(getNumerator() * op2.getDenominator(), getDenominator() * op2.getDenominator());
        Rational var2 = new Rational(op2.getNumerator() * getDenominator(), op2.getDenominator() * getDenominator());
        Rational thingy = new Rational(var1.getNumerator() + var2.getNumerator(), var1.getDenominator());
        thingy.reduce();
        return thingy;
    }
    public AbstractRational subtract(AbstractRational op2) {
        Rational var1 = new Rational(getNumerator() * op2.getDenominator(), getDenominator() * op2.getDenominator());
        Rational var2 = new Rational(op2.getNumerator() * getDenominator(), op2.getDenominator() * getDenominator());
        Rational thingy = new Rational(var1.getNumerator() - var2.getNumerator(), var1.getDenominator());
        thingy.reduce();
        return thingy;
    }
    public AbstractRational multiply(AbstractRational op2) {
        long numer = getNumerator() * op2.getNumerator();
        long denom = getDenominator() * op2.getDenominator();
        Rational rat = new Rational(numer, denom);
        rat.reduce();
        return rat;
    }
    public AbstractRational divide(AbstractRational op2) {
        Rational tempthing = new Rational(getNumerator(), getDenominator());
        return tempthing.multiply(op2.reciprocal());
    }
    public boolean equals(AbstractRational rn2) {
        if ((getNumerator() == rn2.getNumerator()) && (getDenominator() == rn2.getDenominator())) {
            return true;
        }
        return false;
    }
    public void reduce() {
        if (getNumerator() != 0) {
            long gcd = gcd(getNumerator(), getDenominator());
            setNumerator(getNumerator() / gcd);
            setDenominator(getDenominator() / gcd);
        }
    }
    public String toString() {
        if (getNumerator() == 0) {
            return Long.toString(getNumerator());
        }
        if (getDenominator() == 1) {
            return Long.toString(getNumerator());
        }
        reduce();
        String temp = getNumerator() + "/" + getDenominator();
        return temp;
    }
    public boolean decimalTerminates() {
        long yeah = getDenominator();
        while (yeah % 2 ==0) {
            yeah /= 2;
        }
        while (yeah % 5 ==0) {
            yeah /= 5;
        }
        if (yeah == 1) {
            return true;
        }
        return false;
    }
    public int compareTo(AbstractRational arg0) {
        if(arg0.getDenominator() < 0) {
            arg0.setDenominator(-1 * arg0.getDenominator());
            arg0.setNumerator(arg0.getNumerator() * -1);
        }
        arg0.reduce();
        long numer = getNumerator() * arg0.getDenominator();
        long denom = getDenominator() * arg0.getDenominator();
        long otherNumer = arg0.getNumerator() * getDenominator();
        if (numer > otherNumer) {
            return 1;
        } else if (numer < otherNumer) {
            return -1;
        } else {
            return 0;
        }
    }
}
