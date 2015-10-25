package edu.math.shearer.richard;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		this.reduce();
	}

	@Override
	public AbstractRational add(AbstractRational input) {
		int yoloSwag = this.getNumerator() * input.getDenominator();
		int numerator = yoloSwag + input.getNumerator() * getDenominator(); 
		int denominator = getDenominator() * input.getDenominator();
		AbstractRational addedrational = new Rational(numerator , denominator);
		return addedrational; //First, find the GCF of the denominators, and then add the numerators once the denominators are equal
	}

	@Override
	public AbstractRational divide(AbstractRational input) {
		return input.multiply(this.reciprocal()).reciprocal();
	}

	@Override
	public boolean equals(AbstractRational input) {
		return (this.getNumerator() == input.getNumerator() && this.getDenominator() == input.getDenominator());
	}

	@Override
	public AbstractRational multiply(AbstractRational input) {
		int yolo = this.getNumerator() * input.getNumerator(); 
		int swag = this.getDenominator() * input.getDenominator();
		AbstractRational money = new Rational(yolo, swag);
		return money; //Multiply the numerator and the denominator
	}

	@Override
	public AbstractRational reciprocal() {
		this.reduce();
		AbstractRational newRational = new Rational(this.getDenominator(), getNumerator());
		return newRational; //Flip the fraction/rational number
	}

	@Override
	public void reduce() {
		if (this.getNumerator() == 0) {
			int hollaHollaGetDolla = 1;
			setDenominator(hollaHollaGetDolla);
		}
		else {
			int p = getDenominator();
			int q = this.getNumerator();
			int testCode = gcd(p , q);
			int p1 = p / testCode;
			int q1 = q / testCode;
			if (p1 < 0) {
				p1 *= -1;
				q1 *= -1;
			}
			setNumerator(q1);
			setDenominator(p1);
		}
		
	} //reduce for rational numbers, or simplify

	@Override
	public AbstractRational subtract(AbstractRational input) {
		int yoloD1 = input.getDenominator();
		int yoloD2 = getDenominator();
		int swagN1 = input.getNumerator();
		int swagN2 = getNumerator();
		int yolo = (swagN1 / yoloD2);
		int swag = (swagN2 / yoloD1);
		Rational MONEYMONEYMONEY = new Rational(yolo , swag);
		return MONEYMONEYMONEY; //first num/second dem - second num/first denom
	}

	@Override
	public boolean decimalTerminates() {
		int spaghetti = getDenominator();
		while (spaghetti % 2 == 0) {
			spaghetti /= 2; }
		while (spaghetti % 5 == 0) {
			spaghetti /= 5; }
		return (spaghetti == 1); //if a decimal repeats forever, this will correct it.
	}
	@Override
	public java.lang.String toString() {
		if (getDenominator() == 1)
			return "" + getNumerator();
		else 
			return "" + getNumerator() + "/" + getDenominator();
	}
}
