package edu.jenks.test;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;

import static java.lang.System.out;

import org.jdom2.*;

import edu.jenks.google.drive.*;
import edu.jenks.util.*;
import edu.jenks.xml.JDOMHelper;

public class TestRunner {
	
	public final static String XML_FILE_PATH = "testing/testing-config.xml";
	private final static String PACKAGE_ROOT_TAG = "package-root";
	
	private static Document document;
	private static String eclipseStudentRoot;
	private static String googleDriveTurninRoot;
	private static String turninDirSuffix;
	private static List<Element> projects;

	public static void main(String[] args) {
		out.println("Begin TestRunner");
		try {
			initXml();
			for(Element project : projects) {
				processProject(project);
			}
			JDOMHelper.updateXml(document, XML_FILE_PATH);
		} catch (JDOMException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace(System.err);
		} finally {
			out.println("End TestRunner");
		}

	}
	
	private static void processProject(Element project) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		Element studentsElement = project.getChild("students");
		List<Element> students = studentsElement.getChildren("student");
		String testClass = project.getChildText("test-class");
		if(students.size() > 0) {
			out.println("Begin test: " + testClass);
			Testable tester = (Testable)ReflectionUtil.newInstance(testClass);
			initGradesLogger(tester, project);
			processStudents(tester, project, studentsElement, students);
			out.println("End test: " + testClass);
		} else
			out.println("No students for " + testClass);
	}
	
	private static void processStudents(Testable tester, Element project, Element studentsElement, List<Element> students) throws IOException {
		List<Element> studentsToRemove = new ArrayList<Element>(students.size());
		for(Element studentElement : students) {
			Student student = new Student(studentElement.getChildText("first-name"), studentElement.getChildText("last-name"));
			out.println("Begin student " + student.getLastName() + ", " + student.getFirstName());
			initStudentPackage(tester, project, student);
			String feedbackLogPath = initFeedbackLogger(tester, project, student);
			try {
				tester.feedbackLogger.log(Level.INFO, "Begin test of package " + tester.studentPackage + "\r\n" + LoggingUtil.ASTERISKS);
				tester.totalPoints = 0;
				long startTime = System.currentTimeMillis();
				tester.setUp();
				tester.verifySuperClass();
				tester.test();
				long elapsedMillis = System.currentTimeMillis() - startTime;
				tester.feedbackLogger.log(Level.FINE, "Test time in milliseconds: " + elapsedMillis);
				tester.feedbackLogger.log(Level.INFO, "Total for " + tester.studentPackage + ":\n" + tester.totalPoints + " points.\r\n" + LoggingUtil.ASTERISKS + "\r\n");
				if(tester.getPointsAvailable() == tester.totalPoints)
					tester.feedbackLogger.log(Level.INFO, "Congratulation!  You earned all available points!");
				else
					tester.feedbackLogger.log(Level.INFO, "You can earn another " + (tester.getPointsAvailable() - tester.totalPoints) + " points.");
				String percent = NumberFormat.getPercentInstance().format(tester.totalPoints / (double)tester.getPointsAvailable());
				tester.gradesLogger.log(Level.INFO, student.getLastName() + ", " + student.getFirstName() + ": " + tester.totalPoints + " => " + percent);
			} catch(Exception e) {
				tester.feedbackLogger.log(Level.SEVERE, e.getMessage());
			}
			try {
				sendFeedbackLog(feedbackLogPath, student);
				studentsToRemove.add(studentElement);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			out.println("End student " + student.getLastName() + ", " + student.getFirstName());
		}
		for(Element student : studentsToRemove)
			studentsElement.removeContent(student);
	}
	
	private static void sendFeedbackLog(String source, Student student) throws IOException {
		String target = googleDriveTurninRoot + student.getLastName() + student.getFirstName() + turninDirSuffix + "/" + source.substring(source.lastIndexOf("/") + 1);
		CopyFileHelper.copyFeedbackLogFromEclipse(new File(source), new File(target));
	}
	
	private static void initStudentPackage(Testable tester, Element project, Student student) {
		StringBuilder sb = new StringBuilder(project.getChildText(PACKAGE_ROOT_TAG).replace('/', '.'));
		sb.append(student.getLastName().toLowerCase()).append(".").append(student.getFirstName().toLowerCase());
		tester.studentPackage = sb.toString();
	}
	
	private static String initFeedbackLogger(Testable tester, Element project, Student student) throws IOException {
		StringBuilder sb = new StringBuilder(50);
		sb.append(eclipseStudentRoot).append(project.getChildText(PACKAGE_ROOT_TAG));
		sb.append(student.getLastName().toLowerCase()).append("/").append(student.getFirstName().toLowerCase()).append("/");
		sb.append(project.getChildText("name")).append("Feedback").append(".log");
		String path = sb.toString();
		tester.setLogFilePathFeedback(path, student);
		return path;
	}
	
	private static void initGradesLogger(Testable tester, Element project) throws IOException {
		String[] directories = tester.getClass().getName().split(".");
		StringBuilder sb = new StringBuilder(50);
		for(int index = 0; index < directories.length - 1; index++)
			sb.append(directories[index]).append("/");
		String projectName = project.getChildText("name");
		sb.append("grades").append(projectName).append(".log");
		tester.setLogFilePathGrades(sb.toString());
		tester.gradesLogger.log(Level.INFO, "Grades for " + projectName);
	}
	
	private static void initXml() throws JDOMException, IOException {
		document = JDOMHelper.buildDocument(TestRunner.XML_FILE_PATH);
		Element rootElement = document.getRootElement();
		eclipseStudentRoot = rootElement.getChildText("eclipse-student-root");
		Element googleDriveElement = rootElement.getChild("google-drive");
		googleDriveTurninRoot = googleDriveElement.getChildText("turnin-root");
		turninDirSuffix = googleDriveElement.getChildText("turnin-dir-suffix");
		projects = rootElement.getChildren("project");
	}

}
