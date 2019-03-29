package edu.jenks.scrape;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import static java.lang.System.out;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import edu.jenks.scrape.util.SystemInfo;
import edu.jenks.util.LoggingUtil;

public abstract class Scraper {
	public static final Logger LOGGER = Logger.getGlobal();
	public static final String CREDENTIALS_PROPERTIES_PATH = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolCredentials.properties";
	public static final String DB_CONNECTION_ERROR = "Cannot establish DB connection";
	public static final Properties CREDENTIALS_PROPS = new Properties();
	private static final Properties CURRENT_TERM_PROPS = new Properties();
	
	protected static void initLogger() throws IOException {
		LoggingUtil.initLocalFileLogger(LOGGER, SystemInfo.INSTANCE.LOGGING_PATH + "Scraper.log");
		LOGGER.setLevel(Level.ALL);
	}
	
	protected static void initCredentials() throws IOException {
		CREDENTIALS_PROPS.load(new FileInputStream(CREDENTIALS_PROPERTIES_PATH));
	}
	
	static {
		final String currentTermPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "CurrentTerm.properties";
		try {
			initLogger();
			initCredentials();
			CURRENT_TERM_PROPS.load(new FileInputStream(currentTermPropertiesPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static void reportEstimatedRemainingMinutes(long startTimeMillis, int currentCount, int totalCount) {
		double elapsedMillis = System.currentTimeMillis() - startTimeMillis;
		double millisPerUnit = elapsedMillis / currentCount;
		long estimatedRemainingMillis = (long)((totalCount - currentCount) * millisPerUnit);
		out.println("Minutes remaining: " + estimatedRemainingMillis / 1000 / 60);
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
		WEB_CLIENT.getOptions().setPrintContentOnFailingStatusCode(false);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
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
				writer = new FileWriter(SystemInfo.INSTANCE.LOGGING_PATH + fullName + ".log", true);
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
	
	protected HtmlPage authenticatePowerSchool(String urlHome) throws IOException {
		HtmlPage startPage = WEB_CLIENT.getPage(urlHome);
		HtmlInput userInput = startPage.getElementByName("username");
		userInput.setValueAttribute(CREDENTIALS_PROPS.getProperty("username"));
		HtmlInput passwordInput = startPage.getElementByName("password");
		passwordInput.setValueAttribute(CREDENTIALS_PROPS.getProperty("password"));
		HtmlButton submitBtn = (HtmlButton)startPage.getElementById("btnEnter");
		HtmlPage curPage = submitBtn.click();
		LOGGER.info("Authentication Submitted");
		return curPage;
	}
	
	protected HtmlPage authenticatePowerSchoolAdmin() throws IOException {
		HtmlPage curPage = authenticatePowerSchool(CREDENTIALS_PROPS.getProperty("urlHomeAdmin"));
		if(curPage.getUrl().toString().indexOf("home.html") < 0)
			System.out.println("PROBABLE LOGIN FAILURE!");
		return curPage;
	}
	
	protected HtmlPage authenticatePowerSchoolTeacher() throws IOException {
		return authenticatePowerSchool(CREDENTIALS_PROPS.getProperty("urlHomeTeacher"));
	}
	
	public void handleDatabaseConnectionFailure() throws IOException {
		LOGGER.severe(DB_CONNECTION_ERROR);
		throw new IOException(DB_CONNECTION_ERROR);
	}
	
	protected void signOut(HtmlPage curPage) throws IOException {
		clickAnchorByID(curPage, "btnLogout");
		LOGGER.info("Signed Out");
	}
	
	protected void selectSchool(HtmlPage curPage, String school) throws IOException {
		clickAnchorByID(curPage, "schoolContext");
		LOGGER.info("School clicked");
		LOGGER.info("Select school: " + school);
		HtmlSelect select = (HtmlSelect)curPage.getElementByName("Schoolid");
		HtmlOption option = select.getOptionByText(school);
		select.setSelectedAttribute(option, true);
		LOGGER.info("School selected");
	}
	
	protected void selectTerm(HtmlPage curPage) throws IOException {
		clickAnchorByID(curPage, "termContext");
		LOGGER.info("Term clicked.");
		final String termOption = CURRENT_TERM_PROPS.getProperty("TERM_OPTION");
		LOGGER.info("Select term: " + termOption);
		HtmlSelect select = (HtmlSelect)curPage.getElementByName("termid");
		HtmlOption option = select.getOptionByText(termOption);
		select.setSelectedAttribute(option, true);
		LOGGER.info("Term selected");
	}
	
	protected HtmlPage clickAnchorByText(HtmlPage curPage, String text) throws IOException {
		HtmlAnchor link = curPage.getAnchorByText(text);
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
