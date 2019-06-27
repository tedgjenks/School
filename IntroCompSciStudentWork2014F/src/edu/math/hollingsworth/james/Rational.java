package edu.math.hollingsworth.james;
import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational
{
    public static void main(String args[]) {
        Rational r = new Rational(53, 58);
        int t = r.compareTo(new Rational(-524, -417));
        System.out.println(t);
    }
    
    public Rational(long numer, long denom)
    {
        super(numer, denom);
        if(getDenominator() < 0) {
            setDenominator(getDenominator() * -1);
            setNumerator(getNumerator() * -1);
        }
        reduce();
    }
    
    public long gcf(long num1, long num2) {
        if(num1 % num2 == 0) return num2;
        if(num2 % num1 == 0) return num1;
        long result = num1;
        while(true) {
            result += num1;
            if(result % num2 == 0) break;
        }
        return result;
    }
    
    public AbstractRational add(AbstractRational op2) {
        long num1 = getNumerator() * op2.getDenominator();
        long num2 = op2.getNumerator() * getDenominator();
        return new Rational(num1 + num2, getDenominator() * op2.getDenominator());
    }
    
    public int compareTo(AbstractRational op2) {
        long denom = gcf(getDenominator(), op2.getDenominator());
        long num1 = getNumerator() * (denom / getDenominator());
        long num2 = op2.getNumerator() * (denom / op2.getDenominator());
        if(num1 > num2) return -1;
        if(num1 < num2) return 1;
        return 0;
    }
    
    public boolean decimalTerminates() {
        if((getDenominator() == 0 || getDenominator() == 1 || getDenominator() % 2 == 0 || getDenominator() % 5 == 0) && (getDenominator() % 3 != 0)) return true;
        return false;
    }
    
    public AbstractRational divide(AbstractRational op2) {
        return new Rational(getNumerator() * op2.getDenominator(), op2.getNumerator() * getDenominator());
    }
    
    public boolean equals(AbstractRational op2) {
        long denom = gcf(getDenominator(), op2.getDenominator());
        long num1 = getNumerator() * (denom / getDenominator());
        long num2 = op2.getNumerator() * (denom / op2.getDenominator());
        return num1 == num2;
    }
    
    public AbstractRational multiply(AbstractRational op2) {
        return new Rational(op2.getNumerator() * getNumerator(), op2.getDenominator() * getDenominator());
    }
    
    public AbstractRational reciprocal() {
        return new Rational(getDenominator(), getNumerator());
    }
    
    public void reduce() {
        if(getNumerator() != 0) {
            long divisor = gcd(getNumerator(), getDenominator());
            setNumerator(getNumerator() / divisor);
            setDenominator(getDenominator() / divisor);
        }
    }
    
    public AbstractRational subtract(AbstractRational op2) {
        return add(new Rational(-op2.getNumerator(), op2.getDenominator()));
    }
    
    public String toString() {
        if(getDenominator() == 1 || getNumerator() == 0)
            return Long.toString(getNumerator());
        return Long.toString(getNumerator()) + "/" + Long.toString(getDenominator());
    }
}
