            package edu.math.britt.emory;
import edu.jenks.dist.math.AbstractRational; 
public class Rational extends AbstractRational {
    public static void main(String[] args){
        System.out.println("begin");
        Rational r = new Rational(-946, 255); 
        Rational arg0 = new Rational(977, -93);
        int act = r.compareTo(arg0);
        int exp = 1; 
        assert act == exp : "Actual: " + act + "Expected: " + exp;  
        System.out.println("end without error");
    }
    public Rational(long numer, long denom) {
        super(numer, denom); 
        this.cleanUp(); 
        this.reduce(); 
    }
    public AbstractRational reciprocal() {
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        if(numer1 < 0){
            numer1 *= -1; 
            denom1 *= -1; 
            Rational reciprocal = new Rational(denom1, numer1); 
            reciprocal.reduce(); 
            return reciprocal; 
        } 
        Rational reciprocal = new Rational(denom1, numer1); 
        reciprocal.reduce(); 
        return reciprocal; 
    }
    public AbstractRational add(AbstractRational op2) {
        long numer2 = op2.getNumerator(); 
        long denom2 = op2.getDenominator(); 
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        if(denom1 == denom2){
            long numer3 = numer1 + numer2; 
            Rational sum = new Rational(numer3, denom1); 
            sum.reduce(); 
            return sum;
        } 
        long cD = denom1 * denom2; 
        long mult1 = cD/denom1; 
        long mult2 = cD/denom2; 
        numer1 = numer1 * mult1; 
        numer2 = numer2 * mult2; 
        long numer3 = numer1 + numer2; 
        Rational sum = new Rational(numer3, cD);
        sum.reduce(); 
        return sum;
    }
    public AbstractRational subtract(AbstractRational op2) {
        long numer2 = op2.getNumerator(); 
        long denom2 = op2.getDenominator(); 
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        if(denom1 == denom2){
            long numer3 = numer1 - numer2; 
            Rational sub = new Rational(numer3, denom1); 
            sub.reduce(); 
            return sub;
        } 
        long cD = denom1 * denom2; 
        long mult1 = cD/denom1; 
        long mult2 = cD/denom2; 
        numer1 = numer1 * mult1; 
        numer2 = numer2 * mult2; 
        long numer3 = numer1 - numer2; 
        Rational sub = new Rational(numer3, cD);
        sub.reduce(); 
        return sub;
    }
    public AbstractRational multiply(AbstractRational op2) {
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        long numer2 = op2.getNumerator(); 
        long denom2 = op2.getDenominator(); 
        long numerProduct = numer1 * numer2; 
        long denomProduct = denom1 * denom2; 
        Rational product = new Rational(numerProduct, denomProduct);
        product.reduce(); 
        return product; 
    }
    public AbstractRational divide(AbstractRational op2) {
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        AbstractRational recipOP2 = op2.reciprocal();  
        AbstractRational product = multiply(recipOP2); 
        return product; 
    }
    public boolean equals(AbstractRational rn2) {
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        long numer2 = rn2.getNumerator(); 
        long denom2 = rn2.getDenominator(); 
        if(numer1 == numer2 && denom1 == denom2){
            return true; 
        } else{
            return false; 
        }
    }
    public void reduce() {
        long numer1 = this.getNumerator();
        long denom1 = this.getDenominator();
        if(numer1 != 0){
            long gcd = gcd(numer1, denom1); 
            numer1 = numer1/gcd; 
            denom1 = denom1/gcd;
            this.setNumerator(numer1); 
            this.setDenominator(denom1);
        }
    }
    public String toString() { 
        if(this.getNumerator() == 0){
            return "0"; 
        }
        if(this.getDenominator() == 1){
            return String.valueOf(this.getNumerator());
        }
        return this.getNumerator() + "/" + this.getDenominator(); 
    }
    public boolean decimalTerminates() {
        long numer = this.getNumerator();
        long denom = this.getDenominator(); 
        if(denom % 5 == 0){
            while(denom % 5 == 0){
                denom = denom / 5; 
            }
        }
        if(denom % 2 == 0){
            while(denom % 2 == 0){
                denom = denom / 2; 
            }
        }
        if(denom == 1){
            return true; 
        }
        return false; 
    }
    public int compareTo(AbstractRational arg0) {
        this.cleanUp(); 
        ((Rational)arg0).cleanUp();
        if(this.equals(arg0) == true){
            return 0; 
        } 
        long numer = this.getNumerator();
        long denom = this.getDenominator(); 
        long numerArg = arg0.getNumerator();
        long denomArg = arg0.getDenominator(); 
        if(numer < 0 && denom < 0){
            if((numerArg < 0 && denomArg > 0) || (numerArg > 0 && denomArg < 0)){
                return 1; 
            }
        }
        if(numerArg < 0 && denomArg < 0){
            if((numer < 0 && denom > 0) || (numer > 0 && denom < 0)){
                return -1; 
            }
        }
        long cd = denom * denomArg;
        long numerCD1 = numer * denomArg; 
        long numerCD2 = numerArg * denom; 
        if(numerCD1 > numerCD2){
            return 1; 
        } else{
            return -1; 
        }
    }
    public void cleanUp(){
        long numer = this.getNumerator(); 
        long denom = this.getDenominator(); 
        if(denom < 0){ 
            denom = denom * -1;
            numer = numer * -1; 
            this.setDenominator(denom);
            this.setNumerator(numer);
        }
    }
}
