package edu.jenks.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.jenks.google.drive.Student;
import edu.jenks.util.LoggingUtil;
import edu.jenks.util.StringUtil;

public abstract class Testable implements Runnable {
	
	private static final String TEST_METHOD_PREFIX = "test";
	protected static final Object[] EMPTY_OJBECT_ARRAY = {};
	
	protected String studentPackage;
	protected Logger feedbackLogger;
	protected int totalPoints;
	protected boolean continueTesting = true;
	
	private Logger gradesLogger;
	private final TestMethodComparator TEST_METHOD_COMPARATOR = new TestMethodComparator();
	private Thread thread;
	private ThreadGroup threadGroup;
	private Student student;
	
	public abstract void verifySuperClass();
	public abstract int getPointsAvailable();
	
	public void start() {
		feedbackLogger.log(Level.INFO, "Begin test of package " + studentPackage + "\r\n" + LoggingUtil.ASTERISKS);
		setUp();
		verifySuperClass();
		if(thread == null && continueTesting) {
			thread = threadGroup == null ? new Thread(this, student.toString()) : new Thread(threadGroup, this, student.toString());
			thread.start();
		}
	}
	
	public void run() {
		long startTime = System.currentTimeMillis();
		test();
		long elapsedMillis = System.currentTimeMillis() - startTime;
		feedbackLogger.log(Level.FINE, "Test time in milliseconds: " + elapsedMillis);
		feedbackLogger.log(Level.INFO, "Total for " + studentPackage + ":\n" + totalPoints + " points.\r\n" + LoggingUtil.ASTERISKS + "\r\n");
		if(getPointsAvailable() == totalPoints)
			feedbackLogger.log(Level.INFO, "Congratulation!  You earned all available points!");
		else
			feedbackLogger.log(Level.INFO, "You can earn another " + (getPointsAvailable() - totalPoints) + " points.");
		String percent = NumberFormat.getPercentInstance().format(totalPoints / (double)getPointsAvailable());
		logGradesMessage(Level.INFO, student.getLastName() + ", " + student.getFirstName() + ": " + totalPoints + " => " + percent);
	}
	
	public void setUp() {
		continueTesting = true;
	}
	
	public void test() {
		Method[] testMethods = findAndOrderTestMethods();
		for(int index = 0; index < testMethods.length && continueTesting; index++) {
			Method method = testMethods[index];
			String methodName = method.getName();
			feedbackLogger.log(Level.FINE, "Begin test of " + methodName);
			try {
				method.invoke(this, EMPTY_OJBECT_ARRAY);
			} catch(Exception e) {
				logException(methodName, e);
			}
			feedbackLogger.log(Level.FINE, "End test of " + methodName);
		}
	}
	
	private Method[] findAndOrderTestMethods() {
		Class<? extends Testable> executingObjectClass = getClass();
		Method[] declaredMethods = executingObjectClass.getDeclaredMethods();
		List<Method> testMethods = new ArrayList<Method>(declaredMethods.length);
		for(int index = 0; index < declaredMethods.length && continueTesting; index++) {
			Method method = declaredMethods[index];
			String methodName = method.getName();
			int modifiers = method.getModifiers();
			boolean isPublic = Modifier.isPublic(modifiers);
			boolean isStatic = Modifier.isStatic(modifiers);
			if(isPublic && !isStatic && methodName.startsWith(TEST_METHOD_PREFIX))
				testMethods.add(method);
		}
		Collections.sort(testMethods, TEST_METHOD_COMPARATOR);
		return testMethods.toArray(new Method[testMethods.size()]);
	}
	
	public void logException(String method, Exception e) {
		StringBuilder sb = new StringBuilder(100);
		String notice = StringUtil.buildString('*', 20);
		sb.append("Unhandled exception on method: ").append(method).append(notice).append("\n");
		sb.append(e.toString());
		String message = e.getMessage();
		if(message != null)
			sb.append(" - ").append(message);
		Throwable t = e.getCause();
		if(t != null)
			sb.append("; CAUSE: ").append(t.toString());
		sb.append("\n").append(notice).append("End unhandled exception").append(notice);
		feedbackLogger.log(Level.WARNING, sb.toString());
	}
	
	public void setLogFilePathFeedback(String path, Student student) throws IOException {
		feedbackLogger = Logger.getLogger(student + " Feedback: " + getClass().getName());
		LoggingUtil.initLocalFileLogger(feedbackLogger, path);
	}
	
	public Logger getGradesLogger() {
		return gradesLogger;
	}
	
	public void setGradesLogger(Logger gradesLogger) {
		this.gradesLogger = gradesLogger;
	}
	
	public synchronized void logGradesMessage(Level level, String msg) {
		gradesLogger.log(level, msg);
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public ThreadGroup getThreadGroup() {
		return threadGroup;
	}
	public void setThreadGroup(ThreadGroup threadGroup) {
		this.threadGroup = threadGroup;
	}
	
	/*public void execute() {
		Testable testable = getSingleton();
		TestPackageList.testPackages(testable, testable.getLogFilePath());
		logger.log(Level.INFO, "End execute Testable.");
	}*/
}
