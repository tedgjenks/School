package edu.student.yln.yfn;

import edu.jenks.dist.student.AbstractStudent;
import edu.jenks.dist.student.Address;

public class Student extends AbstractStudent {

	public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
		super(firstName, lastName, homeAddress, schoolAddress);
	}

	@Override
	public void setTestScore(int testNumber, double score) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTestScore(int testNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double average() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
