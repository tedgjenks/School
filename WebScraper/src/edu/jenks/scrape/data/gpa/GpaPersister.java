package edu.jenks.scrape.data.gpa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.jenks.scrape.data.Persister;

public class GpaPersister extends Persister {

	private static GpaPersister singleton;
	
	private PreparedStatement insertCourse, selectCourse, insertGrade;
	private PreparedStatement selectAllCoursesForStudent, selectGradesForCourse;
	private PreparedStatement updateGradesGpaWeightedCalc;
	//private PreparedStatement updateGradesStoredGrades;
	private PreparedStatement selectAllMathCoursesForStudent;
	
	public static GpaPersister getInstance(Logger logger) {
		if(singleton == null)
			singleton = new GpaPersister(logger);
		return singleton;
	}
	
	public GpaPersister(Logger logger) {
		super(logger);
	}
	
	public List<Course> getMathCoursesByStudent(Student student) {
		List<Course> courses = new ArrayList<>(50);
		try {
			if(selectAllMathCoursesForStudent == null) {
				String sql = "select course_name, course_number, grade, gpa_wtd_calc, year_term from grades, courses where grades.course_id = courses.course_id and courses.student_id = ? and (course_name like '%Algebra%' or course_name like '%Calculus%' or course_name like '%Geometry%' or course_name like '%Pre-Cal%' or course_name like '%Trig%' or course_name like '%Stat%')";
				selectAllMathCoursesForStudent = openPreparedStatement(sql);
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
			handleSQLException(e);
		}
		return courses;
	}
	
	public List<Student> getStudentsByGrade(int grade) {
		List<Student> students = new ArrayList<>(50);
		Statement studentQuery = null;
		try {
			String sql = "select student_id, f_name, l_name, class_rank, gpa_ps, gpa_hg from student where grade_level=" + grade;
			System.out.println(sql);
			studentQuery = conn.createStatement();
			ResultSet results = studentQuery.executeQuery(sql);
			loadStudentData(students, results);
			results.close();
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(studentQuery);
		}
		return students;
	}
	
	/*private void updateGradesStoredGrades(Grade grade) {
		try {
			updateGradesStoredGrades.setFloat(1, grade.getGpaStoredGrade());
			updateGradesStoredGrades.setFloat(2, grade.getEarnedCreditStoredGrade());
			updateGradesStoredGrades.setLong(3, grade.getGradesId());
			updateGradesStoredGrades.executeUpdate();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}*/
	
	protected void handleSQLException(SQLException e, Student student, Course course) {
		StringBuilder sb = new StringBuilder(100);
		sb.append("SQLException for student ").append(student.getFullName());
		sb.append(", course ").append(course.getCourseName());
		handleSQLException(e, sb.toString());
	}
	
	public void updateGradesGpaWeightedCalc(Grade grade, Student student, Course course) {
		try {
			updateGradesGpaWeightedCalc.setFloat(1, grade.getGpaWeightedCalc());
			updateGradesGpaWeightedCalc.setFloat(2, grade.getEarnedCreditCalc());
			updateGradesGpaWeightedCalc.setLong(3, grade.getGradesId());
			updateGradesGpaWeightedCalc.executeUpdate();
		} catch (SQLException e) {
			handleSQLException(e, student, course);
		}
	}
	
	public List<Student> selectStudentsWithGpaMismatch() {
		List<Student> students = new ArrayList<>(5);
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet results = statement.executeQuery("select student_id, f_name, l_name, class_rank, gpa_ps, gpa_hg from student where gpa_ps != gpa_hg;");
			loadStudentData(students, results);
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(statement);
		}
		return students;
	}
	
	public void updateStudentGpa(List<Student> students) {
		String updateStudentGpaSQL = "update student set gpa_hg = ? where student_id = ?";
		PreparedStatement updateStudentGpa = null;
		try {
			updateStudentGpa = conn.prepareStatement(updateStudentGpaSQL);
			for(int index = students.size() - 1; index >= 0; index--) {
				Student student = students.get(index);
				updateStudentGpa.setFloat(1, student.getGpaHistoricalGrades());
				updateStudentGpa.setLong(2, student.getStudentId());
				updateStudentGpa.addBatch();
			}
			updateStudentGpa.executeBatch();
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(updateStudentGpa);
		}
	}
	
	public List<Grade> getGrades(Course course) {
		List<Grade> grades = new ArrayList<>(2);
		try {
			selectGradesForCourse.setLong(1, course.getStudent().getStudentId());
			selectGradesForCourse.setLong(2, course.getCourseId());
			ResultSet results = selectGradesForCourse.executeQuery();
			while(results.next()) {
				Grade grade = new Grade();
				grade.setGradesId(results.getLong(1));
				grade.setGrade(results.getByte(2));
				grade.setGpaWeightedCalc(results.getFloat(3));
				grade.setEarnedCreditCalc(results.getFloat(4));
				grade.setGpaStoredGrade(results.getFloat(5));
				grade.setEarnedCreditStoredGrade(results.getFloat(6));
				grades.add(grade);
			}
			results.close();
		} catch (SQLException e) {
			handleSQLException(e, course.getStudent(), course);
		}
		return grades;
	}
	
	public List<Course> getCourses(Student student) {
		List<Course> courses = new ArrayList<>(50);
		try {
			selectAllCoursesForStudent.setLong(1, student.getStudentId());
			ResultSet results = selectAllCoursesForStudent.executeQuery();
			while(results.next()) {
				Course course = new Course(student);
				course.setCourseId(results.getLong(1));
				course.setYearTerm(results.getString(2));
				course.setCourseNumber(results.getString(3));
				course.setCourseName(results.getString(4));
				course.setEarnedCredit(results.getFloat(5));
				course.setGradeLevel(results.getByte(6));
				courses.add(course);
			}
			results.close();
		} catch (SQLException e) {
			handleSQLException(e);
		}
		return courses;
	}
	
	//student_id, f_name, l_name, class_rank, gpa_ps, gpa_hg
	private void loadStudentData(List<Student> students, ResultSet results) throws SQLException {
		while(results.next()) {
			Student student = new Student();
			student.setStudentId(results.getLong(1));
			student.setFirstName(results.getString(2));
			student.setLastName(results.getString(3));
			student.setRank(results.getShort(4));
			student.setGpaPowerSchool(results.getFloat(5));
			student.setGpaHistoricalGrades(results.getFloat(6));
			students.add(student);
		}
	}
	
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>(50);
		Statement studentQuery = null;
		try {
			studentQuery = conn.createStatement();
			ResultSet results = studentQuery.executeQuery("select student_id, f_name, l_name, class_rank, gpa_ps, gpa_hg from student");
			loadStudentData(students, results);
			results.close();
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(studentQuery);
		}
		return students;
	}
	
	private void updateCourseObjects(List<Course> courses) throws SQLException {
		for(int index = courses.size() - 1; index >= 0; index--) {
			Course course = courses.get(index);
			selectCourse.setLong(1, course.getStudent().getStudentId());
			selectCourse.setString(2, course.getYearTerm());
			selectCourse.setString(3, course.getCourseNumber());
			ResultSet courseRecord = selectCourse.executeQuery();
			courseRecord.next();
			course.setCourseId(courseRecord.getLong(1));
			courseRecord.close();
		}
	}
	
	public void addCourses(List<Course> courses) {
		try {
			for(int index = courses.size() - 1; index >= 0; index--) {
				Course course = courses.get(index);
				insertCourse.setLong(1, course.getStudent().getStudentId());
				insertCourse.setString(2, course.getYearTerm());
				insertCourse.setString(3, course.getCourseNumber());
				insertCourse.setString(4,  course.getCourseName());
				insertCourse.setFloat(5, course.getEarnedCredit());
				insertCourse.setByte(6, course.getGradeLevel());
				insertCourse.addBatch();
			}
			insertCourse.executeBatch();
			updateCourseObjects(courses);
			for(int index = courses.size() - 1; index >= 0; index--) {
				Course course = courses.get(index);
				List<Grade> grades = course.getGrades();
				for(int gradesIndex = grades.size() - 1; gradesIndex >= 0; gradesIndex--) {
					insertGrade.setLong(1, course.getCourseId());
					insertGrade.setLong(2,  course.getStudent().getStudentId());
					insertGrade.setByte(3, grades.get(gradesIndex).getGrade());
					insertGrade.addBatch();
				}
			}
			insertGrade.executeBatch();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}
	
	public void addStudents(List<Student> students) {
		PreparedStatement insertStudent = null;
		try {
			String insertStudentSQL = "insert into student(student_id, f_name, l_name, grade_level, class_rank, gpa_ps) values(?,?,?,?,?,?)";
			insertStudent = conn.prepareStatement(insertStudentSQL);
			for(int index = students.size() - 1; index >= 0; index--) {
				Student student = students.get(index);
				insertStudent.setLong(1, student.getStudentId());
				insertStudent.setString(2, student.getFirstName());
				insertStudent.setString(3, student.getLastName());
				insertStudent.setByte(4, student.getGradeLevel());
				insertStudent.setShort(5, student.getRank());
				insertStudent.setFloat(6, student.getGpaPowerSchool());
				insertStudent.addBatch();
			}
			insertStudent.executeBatch();
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(insertStudent);
		}
	}
	
	public void clearStudents() {
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.execute("delete from student");
		} catch (SQLException e) {
			handleSQLException(e);
		} finally {
			closeStatement(statement);
		}
	}
	
	protected void initStatements() throws SQLException {
		String insertCourseSQL = "insert into courses(student_id, year_term, course_number, course_name, earned_credit, grade_level) values(?,?,?,?,?,?)";
		insertCourse = openPreparedStatement(insertCourseSQL);
		String selectCourseSQL = "select course_id from courses where student_id = ? and year_term = ? and course_number = ?";
		selectCourse = openPreparedStatement(selectCourseSQL);
		String insertGradesSQL = "insert into grades(course_id, student_id, grade) values(?,?,?)";
		insertGrade = openPreparedStatement(insertGradesSQL);
		String selectAllCoursesForStudentSQL = "select course_id, year_term, course_number, course_name, earned_credit, grade_level from courses where student_id = ?";
		selectAllCoursesForStudent = openPreparedStatement(selectAllCoursesForStudentSQL);
		String selectGradesForCourseSQL = "select grades_id, grade, gpa_wtd_calc, earned_credit_calc, gpa_sg, earned_credit_sg from grades where student_id = ? and course_id = ?";
		selectGradesForCourse = openPreparedStatement(selectGradesForCourseSQL);
		String updateGradesGpaWeightedCalcSQL = "update grades set gpa_wtd_calc = ?, earned_credit_calc = ? where grades_id = ?";
		updateGradesGpaWeightedCalc = openPreparedStatement(updateGradesGpaWeightedCalcSQL);
		/*String updateGradesStoredGradesSQL = "update grades set gpa_sg = ?, earned_credit_sg = ? where grades_id = ?";
		updateGradesStoredGrades = openPreparedStatement(updateGradesStoredGradesSQL);*/
	}
}
