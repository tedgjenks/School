package edu.jenks.cb.songlist.test;

import java.util.*;
import java.util.logging.Level;
import edu.jenks.dist.cb.songlist.*;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class MusicDownloadsTest extends Testable {

	private AbstractMusicDownloads studentMusicDownloads;

	public MusicDownloadsTest() {}
	
	private List<DownloadInfo> createGivenDownloadList() {
		DownloadInfo di1 = new DownloadInfo("Hey Jude");
		di1.setTimesDownloaded(5);
		DownloadInfo di2 = new DownloadInfo("Soul Sister");
		di2.setTimesDownloaded(3);
		DownloadInfo di3 = new DownloadInfo("Aqualung");
		di3.setTimesDownloaded(10);
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		downloadList.addAll(Arrays.asList(di1, di2, di3));
		return downloadList;
	}
	
	private List<String> createGivenTitles() {
		List<String> titles = new ArrayList<String>();
		titles.addAll(Arrays.asList("Lights", "Aqualung", "Soul Sister", "Go Now", "Lights", "Soul Sister"));
		return titles;
	}
	
	private List<DownloadInfo> createExpectedGivenDownloadList() {
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		DownloadInfo di1 = new DownloadInfo("Hey Jude");
		di1.setTimesDownloaded(5);
		DownloadInfo di2 = new DownloadInfo("Soul Sister");
		di2.setTimesDownloaded(5);
		DownloadInfo di3 = new DownloadInfo("Aqualung");
		di3.setTimesDownloaded(11);
		DownloadInfo di4 = new DownloadInfo("Lights");
		di4.setTimesDownloaded(2);
		DownloadInfo di5 = new DownloadInfo("Go Now");
		di5.setTimesDownloaded(1);
		downloadList.addAll(Arrays.asList(di1, di2, di3, di4, di5));
		return downloadList;
	}
	
	private List<DownloadInfo> createCustomDownloadList() {
		DownloadInfo di1 = new DownloadInfo("The Boxer");
		di1.setTimesDownloaded(2);
		DownloadInfo di2 = new DownloadInfo("One");
		di2.setTimesDownloaded(23);
		DownloadInfo di3 = new DownloadInfo("Boogey Man");
		di3.setTimesDownloaded(4);
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		downloadList.addAll(Arrays.asList(di1, di2, di3));
		return downloadList;
	}
	
	private List<String> createCustomTitles() {
		List<String> titles = new ArrayList<String>();
		titles.addAll(Arrays.asList("The Boxer", "Soul Sister", "Go Now", "Lights", "Boogey Man", "Go Now"));
		return titles;
	}
	
	private List<DownloadInfo> createExpectedCustomDownloadList() {
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		DownloadInfo di1 = new DownloadInfo("The Boxer");
		di1.setTimesDownloaded(3);
		DownloadInfo di2 = new DownloadInfo("One");
		di2.setTimesDownloaded(23);
		DownloadInfo di3 = new DownloadInfo("Boogey Man");
		di3.setTimesDownloaded(5);
		DownloadInfo di4 = new DownloadInfo("Soul Sister");
		di4.setTimesDownloaded(1);
		DownloadInfo di5 = new DownloadInfo("Go Now");
		di5.setTimesDownloaded(2);
		DownloadInfo di6 = new DownloadInfo("Lights");
		di6.setTimesDownloaded(1);
		downloadList.addAll(Arrays.asList(di1, di2, di3, di4, di5, di6));
		return downloadList;
	}
	
	private void printTitles(List<DownloadInfo> downloadList) {
		for(DownloadInfo downloadInfo : downloadList)
			System.out.print(downloadInfo.getTitle() + ", ");
		System.out.println();
	}
	
	private void testUpdateDownloads(List<DownloadInfo> downloadList, List<String> titles, List<DownloadInfo> expectedList, String logMessage) {
		studentMusicDownloads.setDownloadList(downloadList);
		studentMusicDownloads.updateDownloads(titles);
		List<DownloadInfo> actualList = studentMusicDownloads.getDownloadList();
		if(expectedList.size() == actualList.size()) {
			boolean pass = true;
			for(int index = expectedList.size() - 1; index >= 0 && pass; index--) {
				DownloadInfo expected = expectedList.get(index);
				DownloadInfo actual = actualList.get(index);
				if(!expected.getTitle().equals(actual.getTitle()) ||
						expected.getTimesDownloaded() != actual.getTimesDownloaded()) {
					pass = false;
					feedbackLogger.log(Level.WARNING, logMessage + " download info did not match after updateDownloads");
				}
			}
			if(pass) {
				totalPoints += 10;
				feedbackLogger.log(Level.INFO, logMessage + " updateDownloads passed");
			}
		} else {
			feedbackLogger.log(Level.WARNING, logMessage + " expected download list was not the same size as actual download list");
			//printTitles(expectedList);
			//printTitles(actualList);
		}
	}
	
	public void testUpdateDownloads() {
		List<DownloadInfo> downloadList = createGivenDownloadList();
		List<String> titles = createGivenTitles();
		List<DownloadInfo> expectedList = createExpectedGivenDownloadList();
		testUpdateDownloads(downloadList, titles, expectedList, "given");
		
		downloadList = createCustomDownloadList();
		titles = createCustomTitles();
		expectedList =  createExpectedCustomDownloadList();
		testUpdateDownloads(downloadList, titles, expectedList, "custom");
	}
	
	public void testGetDownloadInfo() {
		String logMessage = "(given)";
		List<DownloadInfo> downloadList = createGivenDownloadList();
		studentMusicDownloads.setDownloadList(downloadList);
		if(downloadList.get(2) == studentMusicDownloads.getDownloadInfo("Aqualung")) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "passed getDownloadInfo for existing object " + logMessage);
		} else
			feedbackLogger.log(Level.WARNING, "failed getDownloadInfo for existing object " + logMessage);
		
		if(studentMusicDownloads.getDownloadInfo("Happy Birthday") == null) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "passed getDownloadInfo for missing object " +  logMessage);
		} else
			feedbackLogger.log(Level.WARNING, "failed getDownloadInfo for missing object " + logMessage);
		
		logMessage = "(custom)";
		downloadList = createCustomDownloadList();
		studentMusicDownloads.setDownloadList(downloadList);
		if(downloadList.get(2) == studentMusicDownloads.getDownloadInfo("Boogey Man")) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "passed getDownloadInfo for existing object " + logMessage);
		} else
			feedbackLogger.log(Level.WARNING, "failed getDownloadInfo for existing object " + logMessage);
		
		if(studentMusicDownloads.getDownloadInfo("Happy Birthday") == null) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "passed getDownloadInfo for missing object " + logMessage);
		} else
			feedbackLogger.log(Level.WARNING, "failed getDownloadInfo for missing object " + logMessage);
	}

	@Override
	public void setUp() {
		super.setUp();
		try {
			studentMusicDownloads = (AbstractMusicDownloads)ReflectionUtil.newInstance(studentPackage + ".MusicDownloads");
			feedbackLogger.log(Level.INFO, "MusicDownloads instantiated");
			totalPoints += 60;
		} catch(Exception e) {
			feedbackLogger.log(Level.SEVERE, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
	}

	@Override
	public void verifySuperClass() {
		continueTesting = false;
		if(studentMusicDownloads != null) {
			// verify that Object is the superclass
			String superclassName = studentMusicDownloads.getClass().getSuperclass().getName();
			continueTesting = AbstractMusicDownloads.class.getName().equals(superclassName);
			if(!continueTesting)
				feedbackLogger.log(Level.WARNING, "Actual superclass did not match expected superclass");
			else
				feedbackLogger.log(Level.FINE, "Superclass validated.");
		}
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

}
