package edu.jenks.scrape.gpa;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import com.opencsv.*;

import static java.lang.System.out;

import edu.jenks.scrape.util.Students;
import edu.jenks.scrape.util.SystemInfo;
import edu.jenks.util.LoggingUtil;

enum HonorRollType {
	A("A"), AB("AB");
	
	private String value;
	
	private HonorRollType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}

class ScraperComparator implements Comparator<String[]> {

	@Override
	public int compare(String[] o1, String[] o2) {
		return o1[0].compareTo(o2[0]);
	}
	
}

/**
 * Audit A and A/B honor roll from files in the form DO_HR_gradelevel.csv<br>
 * Format student names
 * 
 * @author Jenks
 *
 */
public class AuditHonorRoll {
	
	public static final Logger LOGGER = Logger.getGlobal();
	//public static final byte A_HR = 1;
	//public static final byte AB_HR = 2;
	private static final AuditHonorRoll INSTANCE = new AuditHonorRoll();
	private static final byte DO_NAME_COLUMN = 0;
	private static final byte DO_HR_COLUMN = 3;
	private static final Comparator<String[]> SCRAPER_COMPARATOR = new ScraperComparator();
	
	protected static void initLogger() throws IOException {
		LoggingUtil.initLocalFileLogger(LOGGER, SystemInfo.INSTANCE.LOGGING_PATH + "Audit.log");
		LOGGER.setLevel(Level.ALL);
	}
	
	/**
	 * @param grade
	 * @param hrType
	 * @return file path (new file) of audit information
	 */
	private static String buildScraperFilePath(int grade, HonorRollType hrType, boolean noCommaInName) {
		StringBuilder sb = new StringBuilder(40).append(SystemInfo.INSTANCE.LOGGING_PATH).append(grade).append("_");
		sb.append(hrType.getValue()).append("_HonorRoll");
		if(noCommaInName)
			sb.append("_fml");
		sb.append(".csv");
		return sb.toString();
		
	}
	
	/**
	 * @param grade
	 * @return file path (existing file) of DO honor roll report (A - A/B honor rolls)
	 */
	private static String buildDoFilePath(int grade) {
		return new StringBuilder(40).append(SystemInfo.INSTANCE.LOGGING_PATH).append("DO_HR_")
				.append(grade).append(".csv").toString();
	}

	public static void main(String[] args) {
		out.println("Begin audit.");
		try {
			initLogger();
			for(int grade = 9; grade <= 12; grade++) {
				INSTANCE.loadScraperHonorRoll(grade, HonorRollType.A);
				INSTANCE.loadScraperHonorRoll(grade, HonorRollType.AB);
				INSTANCE.compareDoHonorRoll(grade);
			}
			out.println("End audit without error.");
		} catch(Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		}
	}
	
	private final Map<String, HonorRollRecord> SCRAPER_HR_9 = new HashMap<>(50);
	private final Map<String, HonorRollRecord> SCRAPER_HR_10 = new HashMap<>(50);
	private final Map<String, HonorRollRecord> SCRAPER_HR_11 = new HashMap<>(50);
	private final Map<String, HonorRollRecord> SCRAPER_HR_12 = new HashMap<>(50);
	
	private Map<String, HonorRollRecord> getScraperMap(int grade) {
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
		CSVReader doReader = null;
		try {
			Map<String, HonorRollRecord> scraperMap = getScraperMap(grade);
			doReader = new CSVReader(new FileReader(buildDoFilePath(grade)));
			for(String[] record : doReader.readAll()) {
				String fullName = record[DO_NAME_COLUMN];
				HonorRollType doHrType = record[DO_HR_COLUMN].indexOf('B') >= 0 ? HonorRollType.AB : HonorRollType.A;
				HonorRollRecord scraperHrRecord = scraperMap.remove(fullName);
				if(scraperHrRecord == null) {
					StringBuilder message = new StringBuilder(100);
					message.append(fullName).append(" in DO list, but not scraper; grade ").append(grade).append(System.lineSeparator());
					//TODO log difference
					LOGGER.warning(message.toString());
				} else if(scraperHrRecord.HR_TYPE != doHrType) {
					StringBuilder message = new StringBuilder(100);
					message.append(fullName).append(" in different lists; grade ").append(grade).append(System.lineSeparator());
					message.append("\t");
					appendGrades(message, scraperHrRecord);
					LOGGER.info(message.toString());
				}
			}
			Iterator<String> keySet = scraperMap.keySet().iterator();
			while(keySet.hasNext()) {
				String fullName = keySet.next();
				StringBuilder message = new StringBuilder(100);
				message.append(fullName).append(" in Scraper list, but not DO; grade ").append(grade).append(System.lineSeparator());
				message.append("\t");
				appendGrades(message, scraperMap.get(fullName));
				LOGGER.info(message.toString());
			}
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			doReader.close();
		}
	}
	
	/*private void appendGradesForMissingStudent() {
		CSVReader
	}*/
	
	private void appendGrades(StringBuilder sb, HonorRollRecord hrRecord) {
		sb.append("expected list: ").append(hrRecord.HR_TYPE.getValue()).append("; ");
		for(HistoricalGrade hg : hrRecord.GRADES)
			sb.append(hg).append("; ");
	}
	
	/**
	 * Load custom honor roll into memory and create copy with name formatted fn mn ln
	 * 
	 * @param grade
	 * @param hrType
	 * @throws IOException
	 */
	public void loadScraperHonorRoll(int grade, HonorRollType hrType) throws IOException {
		CSVReader reader = null;
		CSVWriter writer = null;
		try {
			final String filePathWithComma = buildScraperFilePath(grade, hrType, false);
			reader = new CSVReader(new FileReader(filePathWithComma));
			final String filePathNoComma = buildScraperFilePath(grade, hrType, true);
			writer = new CSVWriter(new FileWriter(filePathNoComma));
			List<String[]> records = reader.readAll();
			Collections.sort(records, SCRAPER_COMPARATOR);
			for(String[] record : records) {
				String[] writeRecord = new String[record.length - 1];
				Map<String, HonorRollRecord> scraperMap = getScraperMap(grade);
				String fullName = record[0] + "," + record[1];
				writeRecord[0] = Students.lastNameFirstToFirstNameFirst(fullName);
				HonorRollRecord hrRecord = new HonorRollRecord(hrType);
				for(int recordIndex = 2; recordIndex < record.length; recordIndex++) {
					hrRecord.GRADES.add(new HistoricalGrade(record[recordIndex]));
					writeRecord[recordIndex - 1] = record[recordIndex];
				}
				scraperMap.put(fullName, hrRecord);
				writer.writeNext(writeRecord);
			}
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			if(reader != null)
				reader.close();
			if(writer != null)
				writer.close();
		}
	}
	
	private static class HonorRollRecord {
		final HonorRollType HR_TYPE;
		final List<HistoricalGrade> GRADES = new ArrayList<>(4);
		
		HonorRollRecord(HonorRollType hrType) {
			HR_TYPE = hrType;
		}
	}
	
	private static class HistoricalGrade {
		final String COURSE_NAME;
		final String GRADE;
		
		HistoricalGrade(String gradeRecord) {
			int gradeIndex = gradeRecord.lastIndexOf('-');
			COURSE_NAME = gradeRecord.substring(0, gradeIndex - 2);
			int endGradeIndex = gradeRecord.lastIndexOf(')');
			GRADE = gradeRecord.substring(gradeIndex + 3, endGradeIndex);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return COURSE_NAME + " - (" + GRADE + ")";
		}
		
		
	}

}
