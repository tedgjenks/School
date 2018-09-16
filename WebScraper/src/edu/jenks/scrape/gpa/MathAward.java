package edu.jenks.scrape.gpa;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;
import edu.jenks.scrape.data.gpa.*;
import edu.jenks.util.LoggingUtil;
import static java.lang.System.out;

public class MathAward {
	public static final Logger LOGGER = Logger.getGlobal();
	public static final String LOG_PATH = "C:\\Users\\Jenks\\Documents\\temp\\scraper\\";
	
	protected static void initLogger() throws IOException {
		LoggingUtil.initLocalFileLogger(LOGGER, LOG_PATH + "MathAward.log");
		LOGGER.setLevel(Level.ALL);
	}
	
	static {
		try {
			initLogger();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final MathAward INSTANCE = new MathAward();
	
	public static void main(String[] args) {
		try {
			double highMathScore = 0;
			Student mathAwardWinner = null;
			INSTANCE.PERSISTENCE_INTERFACE.connect();
			LOGGER.info("Connected to DB.");
			List<Student> seniors = INSTANCE.PERSISTENCE_INTERFACE.getStudentsByGrade(12);
			LOGGER.info(seniors.size() + " seniors pulled from DB");
			for(Student student : seniors) {
				float mathScore = INSTANCE.processCourses(student);
				if(mathScore > highMathScore) {
					highMathScore = mathScore;
					mathAwardWinner = student;
				}
			}
			LOGGER.info("Math award winner: " + mathAwardWinner.getFirstName() + " " + mathAwardWinner.getLastName() + " with math points: " + highMathScore);
		} catch(Throwable t) {
			LOGGER.log(Level.SEVERE, t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.PERSISTENCE_INTERFACE.disconnect();
		}
	}
	
	private final GpaPersister PERSISTENCE_INTERFACE =  GpaPersister.getInstance(LOGGER);
	
	private float processCourses(Student student) {
		List<Course> courses = INSTANCE.PERSISTENCE_INTERFACE.getMathCoursesByStudent(student);
		out.println("Process " + courses.size() + " courses for student " + student.getFirstName() + " " + student.getLastName());
		float mathScore = INSTANCE.calculateMathScore(courses);
		LOGGER.info(student.getFirstName() + " " + student.getLastName() + " with math points: " + mathScore);
		return mathScore;
	}
	
	private float calculateMathScore(List<Course> courses) {
		float mathScore = 0;
		int numMathClasses = courses.size();
		if(numMathClasses > 0) {
			float mathGPAPoints = 0;
			int apClasses = 0;
			for(Course course : courses) {
				Grade grade = course.getGrades().get(0);
				float gpa = grade.getGpaWeightedCalc();
				if(gpa <= 0)
					gpa = GPAUtils.weightGpaWithYear(grade.getGrade(), course.getCourseNumber(), course.getYearTerm());
				mathGPAPoints += gpa;
				if(course.getCourseNumber().indexOf("AW") >= 0)
					apClasses++;
			}
			mathScore = (mathGPAPoints / numMathClasses) + (.25F * apClasses) + (.1F * Math.max(0, numMathClasses - 4));
		}
		return mathScore;
	}
}