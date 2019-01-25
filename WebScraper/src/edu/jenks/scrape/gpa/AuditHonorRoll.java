package edu.jenks.scrape.gpa;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;

import static java.lang.System.out;
import edu.jenks.scrap.util.SystemInfo;
import edu.jenks.util.LoggingUtil;

public class AuditHonorRoll {
	
	public static final Logger LOGGER = Logger.getGlobal();
	public static final byte A_HR = 1;
	public static final byte AB_HR = 2;
	private static final AuditHonorRoll INSTANCE = new AuditHonorRoll();
	
	protected static void initLogger() throws IOException {
		LoggingUtil.initLocalFileLogger(LOGGER, SystemInfo.INSTANCE.LOGGING_PATH + "Audit.log");
		LOGGER.setLevel(Level.ALL);
	}
	
	private static String buildScraperFilePath(int grade, int hrType) {
		return new StringBuilder(40).append(SystemInfo.INSTANCE.LOGGING_PATH).append(grade).append("_")
				.append(hrType == A_HR ? "A" : "AB").append("_HonorRoll.csv").toString();
		
	}
	
	private static String buildDoFilePath(int grade) {
		return new StringBuilder(40).append(SystemInfo.INSTANCE.LOGGING_PATH).append("DO_HR_")
				.append(grade).append(".csv").toString();
	}

	public static void main(String[] args) {
		out.println("Begin audit.");
		try {
			initLogger();
			for(int grade = 9; grade <= 12; grade++) {
				INSTANCE.loadScraperHonorRoll(grade, A_HR);
				INSTANCE.loadScraperHonorRoll(grade, AB_HR);
				INSTANCE.compareDoHonorRoll(grade);
			}
		} catch(Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		}
		out.println("End audit without error.");
	}
	
	private final Map<String, Integer> SCRAPER_HR_9 = new HashMap<>(50);
	private final Map<String, Integer> SCRAPER_HR_10 = new HashMap<>(50);
	private final Map<String, Integer> SCRAPER_HR_11 = new HashMap<>(50);
	private final Map<String, Integer> SCRAPER_HR_12 = new HashMap<>(50);
	
	private Map<String, Integer> getScraperMap(int grade) {
		switch(grade) {
			case 9:
				return SCRAPER_HR_9;
			case 10:
				return SCRAPER_HR_10;
			case 11:
				return SCRAPER_HR_11;
			case 12:
				return SCRAPER_HR_12;
			default:
				throw new IllegalArgumentException("Unrecognized grade: " + grade);
		}
	}
	
	public void compareDoHonorRoll(int grade) throws IOException {
		CSVReader reader = null;
		try {
			Map<String, Integer> scraperMap = getScraperMap(grade);
			reader = new CSVReader(new FileReader(buildDoFilePath(grade)));
			for(String[] record : reader.readAll()) {
				String fullName = record[0];
				byte doHrType = record[2].indexOf('B') >= 0 ? AB_HR : A_HR;
				Integer scraperHrType = scraperMap.remove(fullName);
				if(scraperHrType == null)
					LOGGER.warning(fullName + " in DO list, but not scraper; grade " + grade);
				else if(scraperHrType != doHrType)
					LOGGER.warning(fullName + " in different lists; grade " + grade);
			}
			Iterator<String> keySet = scraperMap.keySet().iterator();
			while(keySet.hasNext()) {
				String fullName = keySet.next();
				LOGGER.warning(fullName + " in Scraper list, but not DO; grade " + grade);
			}
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			reader.close();
		}
	}
	
	public void loadScraperHonorRoll(int grade, int hrType) throws IOException {
		CSVReader reader = null;
		try {
			String filePath = buildScraperFilePath(grade, hrType);
			reader = new CSVReader(new FileReader(filePath));
			List<String[]> records = reader.readAll();
			for(String[] record : records) {
				Map<String, Integer> scraperMap = getScraperMap(grade);
				String fullName = record[0] + "," + record[1];
				scraperMap.put(fullName, hrType);
			}
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			reader.close();
		}
	}

}
