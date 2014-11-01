package edu.jenks.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingUtil {
	
	public static final String ASTERISKS = StringUtil.buildString('*', 60);

	public static void initLocalFileLogger(Logger logger, String path) throws IOException {
		FileHandler fileTxt = new FileHandler(path);
		fileTxt.setFormatter(new SimpleFormatter());
		logger.addHandler(fileTxt);
	}
}
