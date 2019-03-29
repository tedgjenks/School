package edu.jenks.scrape.util;

import java.io.IOException;
import java.util.Properties;

public class SystemInfo {
	public static final SystemInfo INSTANCE = new SystemInfo();
	
	private final Properties SYSTEM_PROPERTIES = new Properties();
	public final String LOGGING_PATH;
	public final String RESOURCES_PATH;
	
	private SystemInfo() {
		try {
			SYSTEM_PROPERTIES.load(getClass().getResourceAsStream("SystemInfo.properties"));
			LOGGING_PATH = SYSTEM_PROPERTIES.getProperty("LOGGING_PATH");
			RESOURCES_PATH = SYSTEM_PROPERTIES.getProperty("RESOURCES_PATH");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
