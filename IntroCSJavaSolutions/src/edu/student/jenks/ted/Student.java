package edu.student.jenks.ted;

import edu.jenks.dist.student.AbstractStudent;
import edu.jenks.dist.student.Address;

/**
 * <p><b>Data:</b> three test scores (test numbers 1, 2, and 3).</p>
 * @author Ted Jenks
 *
 */
public class Student extends AbstractStudent {
	
	private double testScore1, testScore2, testScore3;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param homeAddress
	 * @param schoolAddress
	 * @param testScore1
	 * @param testScore2
	 * @param testScore3
	 */
	public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
		super(firstName, lastName, homeAddress, schoolAddress);
		setTestScore(1, testScore1);
		setTestScore(2, testScore2);
		setTestScore(3, testScore3);
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.student.AbstractStudent#setTestScore(int, double)
	 */
	@Override
	public void setTestScore(int testNumber, double score) {
		switch(testNumber) {
			case 1: testScore1 = score;
				break;
			case 2: testScore2 = score;
				break;
			case 3: testScore3 = score;
		}
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.student.AbstractStudent#getTestScore(int)
	 */
	@Override
	public double getTestScore(int testNumber) {
		double score = 0;
		switch(testNumber) {
			case 1: score = testScore1;
				break;
			case 2: score = testScore2;
				break;
			case 3: score = testScore3;
		}
		return score;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.student.AbstractStudent#average()
	 */
	@Override
	public double average() {
		return (testScore1 + testScore2 + testScore3) / 3;
	}

	/**
	 * @see edu.jenks.dist.student.AbstractStudent#toString()
	 * Test score 1: <code>testScore1</code><br>
	 * Test score 2: <code>testScore2</code><br>
	 * Test score 3: <code>testScore3</code><br>
	 * Average test score: <code>average</code>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(super.toString()).append("\n");
		sb.append("Test score 1: ").append(testScore1).append("\n");
		sb.append("Test score 2: ").append(testScore2).append("\n");
		sb.append("Test score 3: ").append(testScore3).append("\n");
		sb.append("Average test score: ").append(average());
		return sb.toString();
	}
}
