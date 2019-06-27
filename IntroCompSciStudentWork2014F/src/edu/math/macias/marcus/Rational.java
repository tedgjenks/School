package edu.math.macias.marcus;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational
{
    public Rational(long numer,long denom){
        super(numer,denom);
        if(denom < 0){
            denom *= -1;
            numer *= -1;
            setDenominator(denom);
            setNumerator(numer);
        }
        reduce();
    }
    public static void main(String[] args){
        System.out.println("Begin.");
        AbstractRational op1 = new Rational(27136,-10240);
        AbstractRational op2 = new Rational(7220,-10240);
        int op7 = op1.compareTo(op2);
        System.out.println(op7);
        //AbstractRational op2 = new Rational(2,5);
        //System.out.println(op1.decimalTerminates());
        //AbstractRational op3 = op1.multiply(op2);
        //AbstractRational op4 = op1.divide(op2);
        //AbstractRational op5 = op1.add(op2);
        //AbstractRational op6 = op1.subtract(op2);
        //System.out.println(op3.getNumerator() + "/" + op3.getDenominator());
        //System.out.println(op1 + " * " + op2 + " = " + op3);
        //System.out.println(op1 + " / " + op2 + " = " + op4);
        //System.out.println(op1 + " + " + op2 + " = " + op5);
        //System.out.println(op1 + " - " + op2 + " = " + op6);
        //System.out.println(op1 + " = " + op2 + " == " + equal);
        System.out.println("End without Error.");
    }
    
    public AbstractRational reciprocal(){
        AbstractRational op3 = new Rational(getDenominator(),getNumerator());
        return op3;
        
    }
    public AbstractRational add(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            AbstractRational op3 = new Rational((getNumerator() + op2.getNumerator()),getDenominator());
            return op3;
        }
        int lcm = (int)getDenominator() * (int)op2.getDenominator();
        setNumerator((lcm / getDenominator()) * getNumerator());
        setDenominator(lcm);
        op2.setNumerator((lcm / op2.getDenominator()) * op2.getNumerator()); 
        op2.setDenominator(lcm);
        
        AbstractRational op3 = new Rational((getNumerator() + op2.getNumerator() ),lcm);
        if(op3.getDenominator() < 0){
            op3.setDenominator(op3.getDenominator() * - 1);
        }
        op3.reduce();
        return op3;
    }
    public AbstractRational subtract(AbstractRational op2){
        if(getDenominator() == op2.getDenominator()){
            AbstractRational op3 = new Rational((getNumerator() - op2.getNumerator()),getDenominator());
            return op3;
        }
        long lcm = getDenominator() * op2.getDenominator();
        setNumerator((lcm / getDenominator()) * getNumerator());
        setDenominator(lcm);
        op2.setNumerator((lcm / op2.getDenominator()) * op2.getNumerator()); 
        op2.setDenominator(lcm);
        AbstractRational op3 = new Rational((getNumerator() - op2.getNumerator() ),lcm);
        op3.reduce();
        return op3;
    }
    public AbstractRational multiply(AbstractRational op2){
        if(op2.getDenominator() < 0 || getDenominator() < 0){
           AbstractRational op3 = new Rational((getNumerator() * op2.getNumerator()) * -1,(getDenominator() * op2.getDenominator()) * -1);
           return op3;
        }
        AbstractRational op3 = new Rational((getNumerator() * op2.getNumerator()),(getDenominator() * op2.getDenominator()));
        op3.reduce();
        return op3;
    }
    public AbstractRational divide(AbstractRational op2){
        if(op2.getDenominator() < 0){
            op2.setDenominator(op2.getDenominator() * - 1);
            op2.setNumerator(op2.getNumerator() * -1);
        }
        if(getDenominator() < 0){
            setDenominator(getDenominator() * - 1);
            setNumerator(getNumerator() * -1);
        }
        AbstractRational op3 = new Rational((getNumerator() * op2.getDenominator()),(getDenominator() * op2.getNumerator()));
        return op3;
    }
    public boolean equals(AbstractRational rn2){
        rn2.reduce();
        AbstractRational op1 = new Rational(getNumerator(),getDenominator());
        op1.reduce();
        if(op1.getNumerator() == rn2.getNumerator() && op1.getDenominator() == rn2.getDenominator()){
            return true;
        }
        return false;
    }
    public void reduce(){
        //System.out.println("original fraction : " + getNumerator() + " / " + getDenominator());
        if(getNumerator() != 0) {
                long divsor = gcd(getNumerator(),getDenominator());
                long numerator = getNumerator();
                long denominator = getDenominator();
                setNumerator(numerator /= divsor);
                setDenominator(denominator /= divsor);
        }
        //System.out.println("reduced fraction : " + getNumerator() + " / " + getDenominator());
    }
    public String toString(){
        //System.out.println(getDenominator());
        String answer = "";
        if(getNumerator() == 0){
            return "0";
        }
        if(getDenominator() == 1){
            answer += getNumerator();
            return answer;
        }
        answer += getNumerator() + "/" + getDenominator();
        return answer;
    }
    public boolean decimalTerminates(){
        AbstractRational op1 = new Rational(getNumerator(),getDenominator());
        op1.reduce();
        System.out.println(op1);
        long den1 = getDenominator();
        long den2 = getDenominator();
        long den3 = getDenominator();
        while(den3 % 2 == 0 || den3 % 5 == 0){
            if(den3 % 2 == 0){
                den3 /= 2;
            }
            if(den3 % 5 == 0){
                den3 /= 5;
            }
        }
        while(den1 % 2 == 0){
            den1 /= 2;
        }
        while(den2 % 5 == 0){
            den2 /= 5;
        }
        System.out.println(den1);
        if(den1 == 1 || den2 == 1 || den3 == 1){
            return true;
        }
        return false;
    }
    public int compareTo(AbstractRational arg0){
        AbstractRational op1 = new Rational(getNumerator(),getDenominator());
        AbstractRational op2 = new Rational(arg0.getNumerator(),arg0.getDenominator());
        if(op2.getDenominator() == op1.getDenominator()){
            if(op1.getNumerator() > op2.getNumerator()){
                return 1;
            }
            if(op1.getNumerator() < op2.getNumerator()){
                return -1;
            }
            return 0;
        }
        int lcm = (int)op1.getDenominator() * (int)op2.getDenominator();
        op1.setNumerator((lcm / op1.getDenominator()) * op1.getNumerator());
        op1.setDenominator(lcm);
        op2.setNumerator((lcm / op2.getDenominator()) * op2.getNumerator()); 
        op2.setDenominator(lcm);
        if(op1.getNumerator() > op2.getNumerator()){
            return 1;
        }
        if(op1.getNumerator() < op2.getNumerator()){
            return -1;
        }
        return 0;
    }
}
