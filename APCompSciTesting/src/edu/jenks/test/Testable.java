package edu.jenks.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.jenks.google.drive.Student;
import edu.jenks.util.LoggingUtil;
import edu.jenks.util.StringUtil;

public abstract class Testable {
	
	private static final String TEST_METHOD_PREFIX = "test";
	private static final TestMethodComparator TEST_METHOD_COMPARATOR = new TestMethodComparator();
	
	protected String studentPackage;
	//protected Logger logger;
	protected Logger gradesLogger;
	protected Logger feedbackLogger;
	protected int totalPoints;
	protected boolean continueTesting = true;
	//protected static final String LOG_FILE_PATH_START = "src/edu/jenks/";
	
	//protected final Class<?>[] EMPTY_CLASS_ARRAY = {};
	protected final Object[] EMPTY_OJBECT_ARRAY = {};
	
	//public abstract String[] getTestMethods();
	//public abstract String getLogFilePath();
	//public abstract Testable getSingleton();
	public abstract void verifySuperClass();
	public abstract int getPointsAvailable();
	
	public void setLogFilePathGrades(String path) throws IOException {
		gradesLogger = Logger.getLogger("Grades: " + getClass().getName());
		LoggingUtil.initLocalFileLogger(gradesLogger, path);
	}
	
	public void setLogFilePathFeedback(String path, Student student) throws IOException {
		feedbackLogger = Logger.getLogger(student + " Feedback: " + getClass().getName());
		LoggingUtil.initLocalFileLogger(feedbackLogger, path);
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
		if(t != null) {
			sb.append("; CAUSE: ").append(t.toString());
			/*message = t.getMessage();
			if(message != null)
				sb.append(" - ").append(message);*/
		}
		sb.append("\n").append(notice).append("End unhandled exception").append(notice);
		feedbackLogger.log(Level.WARNING, sb.toString());
	}
	
	/*public void execute() {
		Testable testable = getSingleton();
		TestPackageList.testPackages(testable, testable.getLogFilePath());
		logger.log(Level.INFO, "End execute Testable.");
	}*/
}
