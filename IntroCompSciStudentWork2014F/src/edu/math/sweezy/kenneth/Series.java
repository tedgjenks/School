package edu.math.sweezy.kenneth;

import edu.jenks.dist.math.*;

public class Series extends AbstractSeries {
	public static void main(String[] args) {
		System.out.println("Begin Tests: ");
		Series instance = new Series();
		double testing1 = instance.sumGeometric(16.0f, 1.0f, -0.5f);
		System.out.println("Testing: " + testing1);
		assert testing1 == 11.0 : "Summed Geometric " + testing1 + " Expected 11.0";
	}
	
	public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
		int tempSum = firstTerm;
		while(firstTerm != lastTerm) {
			firstTerm += commonDifference;
			tempSum += firstTerm;
		}
		return tempSum;
	}

	public double sumGeometric(float firstTerm, float lastTerm, float commonRatio) {
		double tempSum = firstTerm;
		if(commonRatio % 1 == 0){
			if(firstTerm > lastTerm) { 
				while(firstTerm > lastTerm) {
					firstTerm *= commonRatio;
					tempSum += firstTerm;
				}
			} else if(firstTerm < lastTerm) {
				while(firstTerm < lastTerm) {
					firstTerm *= commonRatio;
					tempSum += firstTerm;
				}
			}
		} else {
			while(firstTerm != lastTerm) {
				firstTerm *= commonRatio;
				tempSum += firstTerm;
			}
		}
		return tempSum;
	}
}
