package edu.jenks.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.jenks.util.FileUtil;
import edu.jenks.util.LoggingUtil;

public class TestPackageList {
	
	private static final String TEST_PACKAGES_FILE_NAME = "testPackages.txt";
	private static final String IGNORE_CHAR = "#";

	public static void testPackages(Testable tester, String logPath) {
		try {
			tester.logger = Logger.getLogger(tester.getClass().getName());
			LoggingUtil.initLocalFileLogger(tester.logger, logPath);
			Iterator<String> packages = FileUtil.retrieveLinesFromFile(TEST_PACKAGES_FILE_NAME, IGNORE_CHAR, IGNORE_CHAR + IGNORE_CHAR);
			while(packages.hasNext()) {
				tester.studentPackage = packages.next();
				try {
					tester.logger.log(Level.INFO, "Begin test of package " + tester.studentPackage + "\n" + LoggingUtil.ASTERISKS);
					//logTestMethods(tester);
					tester.totalPoints = 0;
					long startTime = System.currentTimeMillis();
					tester.setUp();
					tester.test();
					long elapsedMillis = System.currentTimeMillis() - startTime;
					tester.logger.log(Level.FINE, "Test time in milliseconds: " + elapsedMillis);
					tester.logger.log(Level.INFO, "Total for " + tester.studentPackage + ":\n" + tester.totalPoints + " points.\n" + LoggingUtil.ASTERISKS + "\n");
				} catch(Exception e) {
					tester.logger.log(Level.WARNING, e.getMessage());
				}
			}
		} catch(IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	/*private static void logTestMethods(Testable tester) {
		String[] testMethods = tester.getTestMethods();
		if(testMethods != null && testMethods.length > 0) {
			StringBuilder sb = new StringBuilder(testMethods.length * 15);
			for(String methodName : testMethods)
				sb.append(methodName).append(", ");
			tester.logger.log(Level.FINE, "Methods to test: " + sb.substring(0, sb.length() - 2));
		} else
			tester.logger.log(Level.FINE, "Methods to test not listed.");
	}*/
}
