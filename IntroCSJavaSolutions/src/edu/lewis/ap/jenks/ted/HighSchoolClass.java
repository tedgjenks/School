/**
 * 
 */
package edu.lewis.ap.jenks.ted;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;

/**
 * @author Ted Jenks
 *
 */
public class HighSchoolClass extends AbstractHighSchoolClass {
	
	/**
	 * @param students
	 */
	public HighSchoolClass(Student[] students) {
		super(students);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.lewis.ap.AbstractHighSchoolClass#getValedictorian()
	 */
	@Override
	public Student[] getValedictorian() {
		double highestGpa = 0;
		for(Student s : students)
			highestGpa = Math.max(highestGpa, s.getGpa());
		List<Student> students = new ArrayList<Student>();
		for(Student s : this.students) {
			//if(Math.abs(s.getGpa() - highestGpa) <= .001)
			if(s.getGpa() == highestGpa)
				students.add(s);
		}
		return students.toArray(new Student[students.size()]);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.lewis.ap.AbstractHighSchoolClass#getHonorsPercent()
	 */
	@Override
	public double getHonorsPercent() {
		int honorsCount = 0;
		for(Student s : students) {
			if(s.isHonors())
				honorsCount++;
		}
		return calculatePercentOfStudentBody(honorsCount);
	}
	
	private double calculatePercentOfStudentBody(int count) {
		return (double)count / students.length * 100;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.lewis.ap.AbstractHighSchoolClass#graduateWithHonorsPercent()
	 */
	@Override
	public double graduateWithHonorsPercent() {
		return graduateAtThreshold(HONORS_GPA);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.lewis.ap.AbstractHighSchoolClass#graduateWithHighestHonorsPercent()
	 */
	@Override
	public double graduateWithHighestHonorsPercent() {
		return graduateAtThreshold(HIGHEST_HONORS_GPA);
	}
	
	private double graduateAtThreshold(double threshold) {
		int count = 0;
		for(Student s : students) {
			if(s.getGpa() >= threshold)
				count++;
		}
		return calculatePercentOfStudentBody(count);
	}

	@Override
	public Student[] getHonorsStudents() {
		List<Student> honorsStudents = new ArrayList<>();
		for(Student s : students) {
			if(s.isHonors())
				honorsStudents.add(s);
		}
		return honorsStudents.toArray(new Student[honorsStudents.size()]);
	}

}
