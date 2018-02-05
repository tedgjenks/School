package edu.jenks.scrape;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import edu.jenks.util.LoggingUtil;

public abstract class Scraper {
	public static final Logger LOGGER = Logger.getGlobal();
	private static final String LOG_PATH = "C:\\Users\\Jenks\\Documents\\temp\\";
	public static final String CREDENTIALS_PROPERTIES_PATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolCredentials.properties";
	private static final String DB_CONNECTION_ERROR = "Cannot establish DB connection";
	
	protected static void initLogger() throws IOException {
		LoggingUtil.initLocalFileLogger(LOGGER, LOG_PATH + "Scraper.log");
		LOGGER.setLevel(Level.ALL);
	}
	
	static {
		try {
			initLogger();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected final WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);
	protected final byte DEFAULT_WAIT_SECONDS = 10;
	protected final byte DEFAULT_JS_ATTEMPTS = 3;
	private final List<String> COLLECTED_ALERTS = new ArrayList<String>();
	private final Map<String, FileWriter> STUDENT_LOGS = new HashMap<>(100);
	
	public Scraper() {
		WEB_CLIENT.setAlertHandler(new CollectingAlertHandler(COLLECTED_ALERTS));
		WEB_CLIENT.setAjaxController(new NicelyResynchronizingAjaxController());
		WEB_CLIENT.getOptions().setCssEnabled(true);
		WEB_CLIENT.getOptions().setRedirectEnabled(true);
		WEB_CLIENT.getOptions().setAppletEnabled(false);
		WEB_CLIENT.getOptions().setJavaScriptEnabled(true);
		WEB_CLIENT.getOptions().setPopupBlockerEnabled(true);
		WEB_CLIENT.getOptions().setTimeout(10000);
		WEB_CLIENT.getOptions().setActiveXNative(false);
		WEB_CLIENT.getOptions().setUseInsecureSSL(true);
		WEB_CLIENT.getOptions().setThrowExceptionOnFailingStatusCode(true);
		WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(false);
		WEB_CLIENT.getOptions().setPrintContentOnFailingStatusCode(true);
	}
	
	protected void waitForJavascript(int seconds, String message) {
		System.out.println("Wait " + seconds + " seconds for " + message);
		WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(seconds * 1000);
		System.out.println("End wait for " + message);
	}
	
	protected void logStudent(String fullName, String message) {
		FileWriter writer;
		try {
			if(STUDENT_LOGS.containsKey(fullName))
				writer = STUDENT_LOGS.get(fullName);
			else {
				writer = new FileWriter(LOG_PATH + fullName + ".log", true);
				STUDENT_LOGS.put(fullName, writer);
			}
			writer.write(message + System.lineSeparator());
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}
	
	protected void assertTrue(boolean expression, String message) throws IOException {
		if(!expression)
			throw new IOException(message);
	}
	
	protected HtmlPage authenticatePowerSchool(String urlHome, Properties credentials) throws IOException {
		HtmlPage startPage = WEB_CLIENT.getPage(urlHome);
		HtmlInput userInput = startPage.getElementByName("username");
		userInput.setValueAttribute(credentials.getProperty("username"));
		HtmlInput passwordInput = startPage.getElementByName("password");
		passwordInput.setValueAttribute(credentials.getProperty("password"));
		HtmlButton submitBtn = (HtmlButton)startPage.getElementById("btnEnter");
		HtmlPage curPage = submitBtn.click();
		LOGGER.info("Authentication Submitted");
		return curPage;
	}
	
	protected HtmlPage authenticatePowerSchoolAdmin() throws IOException {
		Properties credentials = new Properties();
		return authenticatePowerSchool(credentials.getProperty("urlHomeAdmin"), credentials);
	}
	
	protected HtmlPage authenticatePowerSchoolTeacher() throws IOException {
		Properties credentials = new Properties();
		credentials.load(new FileInputStream(CREDENTIALS_PROPERTIES_PATH));
		return authenticatePowerSchool(credentials.getProperty("urlHomeTeacher"), credentials);
	}
	
	public void handleDatabaseConnectionFailure() throws IOException {
		LOGGER.severe(DB_CONNECTION_ERROR);
		throw new IOException(DB_CONNECTION_ERROR);
	}
	
	protected void signOut(HtmlPage curPage) throws IOException {
		clickAnchorByID(curPage, "btnLogout");
		LOGGER.info("Signed Out");
	}
	
	protected HtmlPage clickAnchorByText(HtmlPage curPage, String text) throws IOException {
		HtmlAnchor link = (HtmlAnchor)curPage.getAnchorByText(text);
		return link.click();
	}	
	
	protected HtmlPage clickAnchorByID(HtmlPage curPage, String id) throws IOException {
		HtmlAnchor link = (HtmlAnchor)curPage.getElementById(id);
		return link.click();
	}
	
	private void closeStudentLogs() {
		try {
			Iterator<String> keys = STUDENT_LOGS.keySet().iterator();
			while(keys.hasNext()) {
				FileWriter writer = STUDENT_LOGS.get(keys.next());
				writer.close();
			}
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}
	
	protected void close() {
		WEB_CLIENT.close();
		LOGGER.info("Web client closed");
		closeStudentLogs();
		LOGGER.info("FileWriters for student logs closed");
	}
}
