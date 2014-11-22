package edu.jenks.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import static java.lang.System.out;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import edu.jenks.google.drive.Student;
import edu.jenks.util.LoggingUtil;
import edu.jenks.util.ReflectionUtil;
import edu.jenks.xml.JDOMHelper;

public class TestRunner {
	
	public final static String XML_FILE_PATH = "testing/testing-config.xml";
	
	private static Document document;
	private static String eclipseStudentRoot;
	private static File googleDriveTurninRoot;
	private static String turninDirSuffix;
	private static List<Element> projects;

	public static void main(String[] args) {
		out.println("Begin TestRunner");
		try {
			initXml();
			for(Element project : projects) {
				processProject(project);
			}
		} catch (JDOMException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace(System.err);
		} finally {
			out.println("End TestRunner");
		}

	}
	
	private static void processProject(Element project) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		List<Element> students = project.getChild("students").getChildren("student");
		String testClass = project.getChildText("test-class");
		if(students.size() > 0) {
			out.println("Begin test: " + testClass);
			Testable tester = (Testable)ReflectionUtil.newInstance(testClass);
			initGradesLogger(tester);
			processStudents(tester, project, students);
			out.println("End test: " + testClass);
		} else
			out.println("No students for " + testClass);
	}
	
	private static void processStudents(Testable tester, Element project, List<Element> students) throws IOException {
		for(Element studentElement : students) {
			Student student = new Student(studentElement.getChildText("first-name"), studentElement.getChildText("last-name"));
			out.println("Begin student " + student.getLastName() + ", " + student.getFirstName());
			initFeedbackLogger(tester, project, student);
			try {
				tester.feedbackLogger.log(Level.INFO, "Begin test of package " + tester.studentPackage + "\n" + LoggingUtil.ASTERISKS);
				tester.totalPoints = 0;
				long startTime = System.currentTimeMillis();
				tester.setUp();
				tester.test();
				long elapsedMillis = System.currentTimeMillis() - startTime;
				tester.feedbackLogger.log(Level.FINE, "Test time in milliseconds: " + elapsedMillis);
				tester.feedbackLogger.log(Level.INFO, "Total for " + tester.studentPackage + ":\n" + tester.totalPoints + " points.\n" + LoggingUtil.ASTERISKS + "\n");
				tester.gradesLogger.log(arg0, arg1, arg2);
			} catch(Exception e) {
				tester.feedbackLogger.log(Level.WARNING, e.getMessage());
			}
			out.println("End student " + student.getLastName() + ", " + student.getFirstName());
		}
	}
	
	private static void initFeedbackLogger(Testable tester, Element project, Student student) throws IOException {
		StringBuilder sb = new StringBuilder(50);
		sb.append(eclipseStudentRoot).append(project.getChildText("package-root"));
		sb.append(student.getLastName()).append("/").append(student.getFirstName()).append("/");
		sb.append(project.getChildText("name")).append(".log");
		tester.setLogFilePathFeedback(sb.toString());
	}
	
	private static void initGradesLogger(Testable testable) throws IOException {
		String[] directories = testable.getClass().getName().split(".");
		StringBuilder sb = new StringBuilder(50);
		for(int index = 0; index < directories.length - 1; index++)
			sb.append(directories[index]).append("/");
		sb.append("grades.log");
		testable.setLogFilePathGrades(sb.toString());
	}
	
	private static void initXml() throws JDOMException, IOException {
		document = JDOMHelper.buildDocument(TestRunner.XML_FILE_PATH);
		Element rootElement = document.getRootElement();
		eclipseStudentRoot = rootElement.getChildText("eclipse-student-root");
		Element googleDriveElement = rootElement.getChild("google-drive");
		googleDriveTurninRoot = new File(googleDriveElement.getChildText("turnin-root"));
		turninDirSuffix = googleDriveElement.getChildText("turnin-dir-suffix");
		projects = rootElement.getChildren("project");
	}

}
