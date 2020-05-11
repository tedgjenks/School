/**
 * 
 */
package edu.jenks.scrape.data.gpa;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author tedgj
 *
 */
public class MathGpaPersister {
	
	private static GpaPersister gpaPersister;
	private static MathGpaPersister singleton;
	private PreparedStatement selectAllMathCoursesForStudent;

	public static MathGpaPersister getInstance(Logger logger) {
		if(singleton == null)
			singleton = new MathGpaPersister(logger);
		return singleton;
	}
	
	private MathGpaPersister(Logger logger) {
		gpaPersister = GpaPersister.getInstance(logger);
	}
	
	public List<Course> getMathCoursesByStudent(Student student) {
		List<Course> courses = new ArrayList<>(50);
		try {
			if(selectAllMathCoursesForStudent == null) {
				String sql = "select course_name, course_number, grade, gpa_wtd_calc, year_term from grades, courses where grades.course_id = courses.course_id and courses.student_id = ? and (course_name like '%Algebra%' or course_name like '%Calculus%' or course_name like '%Geometry%' or course_name like '%Pre-Cal%' or course_name like '%Trig%' or course_name like '%Stat%')";
				selectAllMathCoursesForStudent = gpaPersister.openPreparedStatement(sql);
			}
			selectAllMathCoursesForStudent.setLong(1, student.getStudentId());
			ResultSet rs = selectAllMathCoursesForStudent.executeQuery();
			while(rs.next()) {
				Course course = new Course(student);
				course.setCourseName(rs.getString(1));
				course.setCourseNumber(rs.getString(2));
				List<Grade> grades = new ArrayList<>(1);
				course.setGrades(grades);
				Grade grade = new Grade(rs.getByte(3));
				grades.add(grade);
				grade.setGpaWeightedCalc(rs.getFloat(4));
				course.setYearTerm(rs.getString(5));
				courses.add(course);
			}
			rs.close();
		} catch (SQLException e) {
			gpaPersister.handleSQLException(e);
		}
		return courses;
	}
	
	public List<Student> getStudentsByGrade(int grade) {
		return gpaPersister.getStudentsByGrade(grade);
	}
	
	public void disconnect() {
		gpaPersister.disconnect();
	}
	
	public void connect() throws IOException {
		gpaPersister.connect();
	}
}
