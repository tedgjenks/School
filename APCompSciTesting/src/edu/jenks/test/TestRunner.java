package edu.jenks.test;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

import org.jdom2.*;

import edu.jenks.google.drive.*;
import edu.jenks.util.*;
import edu.jenks.xml.JDOMHelper;

public class TestRunner {
	
	public static final String XML_FILE_PATH = "testing/testing-config.xml";
	private static final String PACKAGE_ROOT_TAG = "package-root";
	private static final String DEADLINE_TAG = "deadline";
	private static final String DUE_DATE_TAG = "due-date";
	private static final String PENALTY_TAG = "penalty";
	public static final String PROJECT_NAME_TAG = "name";
	private static final String PROJECT_MAX_RUNTIME_SECS_TAG = "max-runtime-secs";
	private static final String FORMATTED_DATE;
	
	private static Document document;
	private static boolean testMode;
	private static String eclipseStudentRoot;
	private static String googleDriveTurninRoot;
	private static String turninDirSuffix;
	private static List<Element> projects;
	private static Map<String, Logger> gradesLoggers = new HashMap<String, Logger>();
	
	static {
		DateFormat sdf = SimpleDateFormat.getInstance();
		((SimpleDateFormat)sdf).applyPattern("yyyy-MM-dd_HH-mm-ss");
		FORMATTED_DATE = sdf.format(new Date());
	}

	public static void main(String[] args) {
		out.println("Begin TestRunner");
		try {
			System.setSecurityManager(new CustomSecurityManager());
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
			processStudents(testClass, project, studentsElement, students);
			out.println("End test: " + testClass);
		} else
			out.println("No students for " + testClass);
	}
	
	private static void processStudents(String testClass, Element project, Element studentsElement, List<Element> students) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ThreadGroup threadGroup = new ThreadGroup(project.getChildText(PROJECT_NAME_TAG));
		Map<Student, String> studentFeedbackLogPath = new HashMap<Student, String>();
		for(Element studentElement : students) {
			Testable tester = (Testable)ReflectionUtil.newInstance(testClass);
			initGradesLogger(tester, project);
			processLatePenalty(tester, project);
			Student student = new Student(studentElement.getChildText("first-name"), studentElement.getChildText("last-name"));
			student.setStudentElement(studentElement);
			initStudentPackage(tester, project, student);
			String feedbackLogPath = initFeedbackLogger(tester, project, student);
			studentFeedbackLogPath.put(student, feedbackLogPath);
			tester.setThreadGroup(threadGroup);
			tester.setStudent(student);
			tester.start();
		}
		TestableMonitor monitor = new TestableMonitor(threadGroup, project.getChildText(PROJECT_NAME_TAG), calcMaxRuntimeMillis(project), testMode);
		monitor.start();
		try {
			monitor.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
		if(!testMode) {
			out.println("Sending feedback " + StringUtil.buildString('*', 50));
			try {
				for(Student student : studentFeedbackLogPath.keySet()) {
					String feedbackLogPath = studentFeedbackLogPath.get(student);
					sendFeedbackLog(feedbackLogPath, student);
					studentsElement.removeContent(student.getStudentElement());
				}
				System.out.println("Feedback sent " + StringUtil.buildString('*', 50));
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		} else
			out.println("TESTING MODE - no feedback sent & testing-config.xml not cleared!");
	}
	
	private static long calcMaxRuntimeMillis(Element project) {
		String maxRuntimeSecs = project.getChildText(PROJECT_MAX_RUNTIME_SECS_TAG);
		return Long.parseLong(maxRuntimeSecs) * 1000;
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
		sb.append(project.getChildText(PROJECT_NAME_TAG)).append("Feedback");
		sb.append(FORMATTED_DATE).append(".log");
		String path = sb.toString();
		tester.setLogFilePathFeedback(path, student);
		return path;
	}
	
	private static void processLatePenalty(Testable tester, Element project) throws IOException {
		Element deadlineElement = project.getChild(DEADLINE_TAG);
		if(deadlineElement != null) {
			String dueDateText = deadlineElement.getChildText(DUE_DATE_TAG);
			String penaltyText = deadlineElement.getChildText(PENALTY_TAG);
			if(dueDateText != null && !dueDateText.isEmpty() && penaltyText != null && !penaltyText.isEmpty()) {
				int penalty = Integer.parseInt(penaltyText);
				if(penalty > 0) {
					String[] dateTokens = dueDateText.split("/");
					int month = Integer.parseInt(dateTokens[0]), day = Integer.parseInt(dateTokens[1]), year = Integer.parseInt(dateTokens[2]);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					boolean applyPenalty = false;
					int calYear = calendar.get(Calendar.YEAR);
					if(calYear > year)
						applyPenalty = true;
					else if(calYear == year) {
						int calMonth = calendar.get(Calendar.MONTH) + 1; // cal month starts at 0
						if(calMonth > month)
							applyPenalty = true;
						else if(calMonth == month) {
							int calDay = calendar.get(Calendar.DAY_OF_MONTH); // cal day starts at 1
							System.out.println("Calendar day: " + calDay);
							if(calDay > day)
								applyPenalty = true;
						}
					}
					if(applyPenalty)
						tester.latePenalty = penalty;
				}
			}
		}
	}
	
	private static void initGradesLogger(Testable tester, Element project) throws IOException {
		String[] directories = tester.getClass().getName().split(".");
		StringBuilder sb = new StringBuilder(50);
		for(int index = 0; index < directories.length - 1; index++)
			sb.append(directories[index]).append("/");
		String projectName = project.getChildText(PROJECT_NAME_TAG);
		sb.append("grades").append(projectName).append(".log");
		Logger gradesLogger = gradesLoggers.get(projectName);
		if(gradesLogger == null) {
				gradesLogger = Logger.getLogger("Grades: " + tester.getClass().getName());
				LoggingUtil.initLocalFileLogger(gradesLogger, sb.toString());
				gradesLogger.log(Level.INFO, "Grades for " + projectName);
				gradesLoggers.put(projectName, gradesLogger);
		}
		tester.setGradesLogger(gradesLogger);
	}
	
	private static void initXml() throws JDOMException, IOException {
		document = JDOMHelper.buildDocument(TestRunner.XML_FILE_PATH);
		Element rootElement = document.getRootElement();
		testMode = Boolean.valueOf(rootElement.getAttributeValue("test-mode"));
		eclipseStudentRoot = rootElement.getChildText("eclipse-student-root");
		Element googleDriveElement = rootElement.getChild("google-drive");
		googleDriveTurninRoot = googleDriveElement.getChildText("turnin-root");
		turninDirSuffix = googleDriveElement.getChildText("turnin-dir-suffix");
		projects = rootElement.getChildren("project");
	}

}
