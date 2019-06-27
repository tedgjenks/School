
package edu.math.whitt.rose;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational
{
    
    public static void main(String[] args) {
        //System.out.println("test");
        Rational rat = new Rational(-87, 175);
        System.out.println("Original Rational Number: " + rat.toString());
        
        System.out.println("Reciprocal of Rational Number: " + rat.reciprocal().toString());
        
        Rational op2Number = new Rational(1, 3);
        
        System.out.println("Original Fed Number: " + op2Number.toString());
        
        System.out.println("Rational and Fed Numbers Added: " + rat.add(op2Number).toString());
        
        System.out.println("Rational and Fed Numbers Subtracted: " + rat.subtract(op2Number).toString());
        
        System.out.println("Rational and Fed Numbers Multiplied: " + rat.multiply(op2Number).toString());
        
        System.out.println("Rational and Fed Numbers Divided: " + rat.divide(op2Number).toString());
        
        Rational rn2Number = new Rational(1, -2);
        System.out.println("Original Rational Fed Number: " + rn2Number.toString());
        System.out.println("Is The Rational Number Equal to The Parameter? " + rat.equals(rn2Number));
        
        System.out.println("Is the Rational Number a Terminating Decimal? " + rat.decimalTerminates());
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        
        Rational arg0 = new Rational(-181, -225);
        
        System.out.println(rat.compareTo(arg0));
        
        //int testNum = 32;
        //int factor;
        //for (int i = 2; i <= testNum; i++) {
            //if (testNum % i == 0) {
                //factor = i;
                //if ((factor == 2) || (factor == 5)) {
                    //System.out.println(i + " true");
                //} else if ((factor % 2 == 0) || (factor % 5 == 0)) {
                    //System.out.println(i + " true");
                //} else {
                    //System.out.println(i + " false");
                //}
            //}
        //}
        
        for (int i = 1; i < 21; i++) {
            Rational thing = new Rational(1, i);
            System.out.println("Is the Rational Number a Terminating Decimal? " + thing + ": " + thing.decimalTerminates());
        
        }
        
        
    }
    public Rational(long numer, long denom) throws java.lang.IllegalArgumentException {
        super(numer, denom);
        long num = getNumerator();
        long den = getDenominator();
        if (den < 0) {
            den *= -1;
            num *= -1;
        }
        setNumerator(num);
        setDenominator(den);
        reduce();
    }
    
    public AbstractRational reciprocal() {
        //if (getDenominator() == 0) {
            //AbstractRational declareRecip = new Rational(getDenominator() - getDenominator(), getNumerator() - getNumerator());
            //declareRecip.reduce();
            //return declareRecip;
        //}
        if (getNumerator() != 0) {
            //if (getDenominator() != 0) {
                if (getNumerator() < 0) {
                    AbstractRational declareRecip = new Rational(getDenominator() * -1, getNumerator() * -1);
                    declareRecip.reduce();
                    return declareRecip;
                } else {
                    AbstractRational declareRecip = new Rational(getDenominator(), getNumerator());
                    declareRecip.reduce();
                    return declareRecip;
                }
            //} else {
                //AbstractRational declareRecip = new Rational(0, 0);
                //declareRecip.reduce();
                //return declareRecip;
            //}
        } else {
            return null;
        }
    }
    public AbstractRational add(AbstractRational op2) {
        long ratDenom;
        long ratNumer;
        if (getDenominator() < 0) {
            ratDenom = getDenominator() * -1;
            ratNumer = getNumerator() * -1;
        } else {
            ratDenom = getDenominator();
            ratNumer = getNumerator();
        }
        
        long opDenom;
        long opNumer;
        if (op2.getDenominator() < 0) {
            opDenom = op2.getDenominator() * -1;
            opNumer = op2.getNumerator() * -1;
        } else {
            opDenom = op2.getDenominator();
            opNumer = op2.getNumerator();
        }
        
        long commonDenom = opDenom * ratDenom;
        
        long ratFactor = commonDenom / ratDenom;
        long newRatNumer = ratFactor * ratNumer;
        
        //int newWholeDenom = ratFactor * ratDenom;
        
        long opFactor = commonDenom / opDenom;
        long newOpNumer = opFactor * opNumer;
        
        long newWholeNumerator = newRatNumer + newOpNumer;
        
        Rational opAndRatAdded = new Rational(newWholeNumerator, commonDenom);
        opAndRatAdded.reduce();
        return opAndRatAdded;
    }
    public AbstractRational subtract(AbstractRational op2) {
        long ratDenom;
        long ratNumer;
        if (getDenominator() < 0) {
            ratDenom = getDenominator() * -1;
            ratNumer = getNumerator() * -1;
        } else {
            ratDenom = getDenominator();
            ratNumer = getNumerator();
        }
        
        long opDenom;
        long opNumer;
        if (op2.getDenominator() < 0) {
            opDenom = op2.getDenominator() * -1;
            opNumer = op2.getNumerator() * -1;
        } else {
            opDenom = op2.getDenominator();
            opNumer = op2.getNumerator();
        }
        
        long commonDenom = opDenom * ratDenom;
        
        long ratFactor = commonDenom / ratDenom;
        long newRatNumer = ratFactor * ratNumer;
        
        //int newWholeDenom = ratFactor * ratDenom;
        
        long opFactor = commonDenom / opDenom;
        long newOpNumer = opFactor * opNumer;
        
        long newWholeNumerator = newRatNumer - newOpNumer;
        
        Rational opAndRatSubtracted = new Rational(newWholeNumerator, commonDenom);
        opAndRatSubtracted.reduce();
        return opAndRatSubtracted;
    }
    public AbstractRational multiply(AbstractRational op2) {
        long ratDenom;
        long ratNumer;
        if (getDenominator() < 0) {
            ratDenom = getDenominator() * -1;
            ratNumer = getNumerator() * -1;
        } else {
            ratDenom = getDenominator();
            ratNumer = getNumerator();
        }
        
        long opDenom;
        long opNumer;
        if (op2.getDenominator() < 0) {
            opDenom = op2.getDenominator() * -1;
            opNumer = op2.getNumerator() * -1;
        } else {
            opDenom = op2.getDenominator();
            opNumer = op2.getNumerator();
        }
        
        long multipliedNumer = ratNumer * opNumer;
        long multipliedDenom = ratDenom * opDenom;
        
        Rational opAndRatMultiplied = new Rational(multipliedNumer, multipliedDenom);
        opAndRatMultiplied.reduce();
        return opAndRatMultiplied;
    }
    public AbstractRational divide(AbstractRational op2) {
        long ratDenom;
        long ratNumer;
        if (getDenominator() < 0) {
            ratDenom = getDenominator() * -1;
            ratNumer = getNumerator() * -1;
        } else {
            ratDenom = getDenominator();
            ratNumer = getNumerator();
        }
        
        long opDenom;
        long opNumer;
        if (op2.getDenominator() < 0) {
            opDenom = op2.getDenominator() * -1;
            opNumer = op2.getNumerator() * -1;
        } else {
            opDenom = op2.getDenominator();
            opNumer = op2.getNumerator();
        }
        
        
        long dividedNumer = ratNumer * opDenom;
        long dividedDenom = ratDenom * opNumer;
        
        Rational opAndDivided = new Rational(dividedNumer, dividedDenom);
        opAndDivided.reduce();
        return opAndDivided;
    }
    public boolean equals(AbstractRational rn2) {
        AbstractRational rationalForEquals = new Rational(getNumerator(), getDenominator());
        rationalForEquals.reduce();
        rn2.reduce();
        if (getNumerator() == rn2.getNumerator() && getDenominator() == rn2.getDenominator()) {
            return true;
        } else {
            return false;
        }
    }
    public void reduce() {
        if ((getNumerator() != 0) && (getDenominator() != 0)) {
            long lowestRatFactor = gcd(getNumerator(), getDenominator());
            setNumerator(getNumerator() / lowestRatFactor);
            setDenominator(getDenominator() / lowestRatFactor);
        }
    }
    public String toString() {
        AbstractRational rationalForString = new Rational(getNumerator(), getDenominator());
        rationalForString.reduce();
        if (getNumerator() == 0) {
            return "0";
        } else {
            if (getDenominator() == 1) {
                String convertToString = getNumerator() + "";
                return convertToString;
            } else if (getDenominator() == 1) {
                String convertToString = getNumerator() + "";
                return convertToString;
            } else {
                String convertToString = getNumerator() + "/" + getDenominator();
                return convertToString;
            }
        }
    }
    public boolean decimalTerminates() {
      /*long num = 0;
        if (getDenominator() == 1) {
           num = 1;
        } else {
            for (long factor = 2; factor <= getDenominator(); factor++) {
                
                if (getDenominator() % factor == 0) {
                    
                    if ((factor == 2) || (factor == 5)) {
                        num = 1;
                    } else if ((factor % 2 == 0) || (factor % 5 == 0)) {
                        num = 1;
                    } else {
                        num = num;
                    }
                }
                
            }
        }
        if (num == 1) {
            return true;
        } else {
            return false;
        }*/
        long num = getDenominator();
        while (num % 5 == 0) {
            num /= 5;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        
        if (num == 1) {
            return true;
        } else  {
            return false;
        }
    }
    public int compareTo(AbstractRational arg0) {
        //AbstractRational rationalNumber = new Rational(getNumerator(), getDenominator());
        check();
        ((Rational)arg0).check();
        
        //return the numerator of the difference of the two
        long thing = subtract(arg0).getNumerator();
        //long compare = 0;
        if (thing == 0) {
            return 0;
        } else if (thing < 0) {
            return -1;
        } else {
            return 1;
        }
    }
    private void check() {
        long num = getNumerator();
        long den = getDenominator();
        if (den < 0) {
            den *= -1;
            num *= -1;
        }
        setNumerator(num);
        setDenominator(den);
        reduce();
    }
    
    
}
