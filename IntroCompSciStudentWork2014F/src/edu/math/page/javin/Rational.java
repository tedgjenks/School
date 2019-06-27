package edu.math.page.javin;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational{
	public static void main(String[] args) {
		Rational hello = new Rational(1, 1);
		Rational goodbye = new Rational(1, 1);
		hello.setNumerator(-171);
		hello.setDenominator(-191);
		goodbye.setNumerator(593);
		goodbye.setDenominator(471);
		System.out.println(hello.compareTo(goodbye));
	}
	public Rational(long numer, long denom) throws IllegalArgumentException {
		super(numer, denom);
		if (denom < 0) {
			setDenominator(Math.abs(getDenominator()));
			setNumerator(getNumerator() * -1);
		}
	}
	public AbstractRational reciprocal() {
		Rational recip = new Rational(getDenominator(), getNumerator());
		return recip;
	}
	public AbstractRational add(AbstractRational op2) {
		Rational add1 = new Rational(getNumerator() * op2.getDenominator(), getDenominator() * op2.getDenominator());
		Rational add2 = new Rational(op2.getNumerator() * getDenominator(), op2.getDenominator() * getDenominator());
		Rational sum = new Rational(add1.getNumerator() + add2.getNumerator(), add1.getDenominator());
		return sum;
	}
	private void negativeNumerator() {
		setNumerator(getNumerator() * -1);
	}
	public AbstractRational subtract(AbstractRational op2) {
		((Rational) op2).negativeNumerator();
		return add(op2);
	}
	public AbstractRational multiply(AbstractRational op2) {
		long numerator = getNumerator() * op2.getNumerator();
		long denominator = getDenominator() * op2.getDenominator();
		Rational multiplied = new Rational(numerator, denominator);
		return multiplied;
	}
	public AbstractRational divide(AbstractRational op2) {
		return this.multiply(op2.reciprocal());
	}
	public boolean equals(AbstractRational rn2) {
		return false;
	}
	public void reduce() {
		if (getNumerator() != 0){
			long gcd = gcd(getNumerator(), getDenominator());
			setNumerator(getNumerator() /  gcd);
			setDenominator(getDenominator() / gcd);
		}
	}
	public String toString(){
		if (getNumerator() == 0) {
			return getNumerator() + "";
		}
		reduce();
		if(this.getDenominator() == 1) {
			return String.valueOf(getNumerator());
		}
		return getNumerator() + "/" + getDenominator();
	}
	public boolean decimalTerminates() {
		long num = getDenominator();
		while (num % 2 == 0) {
			num /= 2;
		}
		while (num % 5 == 0) {
			num /= 5;
		}
		if (num == 1) {
			return true;
		}
		return false;
	}
	public int compareTo(AbstractRational arg0) {
		if (arg0.getDenominator() < 0) {
			arg0.setDenominator(Math.abs(arg0.getDenominator()));
			arg0.setNumerator(arg0.getNumerator() * -1);
		}
		if (getDenominator() < 0) {
			setDenominator(Math.abs(getDenominator()));
			setNumerator(getNumerator() * -1);
		}
		arg0.reduce();
		long firstNum = getNumerator() * arg0.getDenominator();
		long nextNum = arg0.getNumerator() * getDenominator();
		if (firstNum > nextNum) {
			return 1;
		}else if (firstNum < nextNum) {
			return -1;
		}
		return 0;
	} 
}
