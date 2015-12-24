package edu.cb.songlist.jenks.ted.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cb.songlist.jenks.ted.MusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloadsTest {
	
	private List<DownloadInfo> createDownloadList() {
		DownloadInfo di1 = new DownloadInfo("Hey Jude");
		di1.setTimesDownloaded(5);
		DownloadInfo di2 = new DownloadInfo("Soul Sister");
		di2.setTimesDownloaded(3);
		DownloadInfo di3 = new DownloadInfo("Aqualung");
		di3.setTimesDownloaded(10);
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		downloadList.add(di1);
		downloadList.add(di2);
		downloadList.add(di3);
		return downloadList;
	}

	@Test
	public void testGetDownloadInfo() {
		List<DownloadInfo> downloadList = createDownloadList();
		MusicDownloads md = new MusicDownloads();
		md.setDownloadList(downloadList);
		assertSame(downloadList.get(2), md.getDownloadInfo("Aqualung"));
		assertNull(md.getDownloadInfo("Happy Birthday"));
	}
	
	@Test
	public void testUpdateDownloads() {
		List<DownloadInfo> downloadList = createDownloadList();
		MusicDownloads md = new MusicDownloads();
		md.setDownloadList(downloadList);
		List<String> titles = createNewTitles();
		md.updateDownloads(titles);
		List<DownloadInfo> expectedList = createExpectedDownloadList();
		List<DownloadInfo> actualList = md.getDownloadList();
		assertEquals(expectedList.size(), actualList.size());
		for(int index = expectedList.size() - 1; index >= 0; index--) {
			DownloadInfo expected = expectedList.get(index);
			DownloadInfo actual = actualList.get(index);
			assertEquals(expected.getTitle(), actual.getTitle());
			assertEquals(expected.getTimesDownloaded(), actual.getTimesDownloaded());
		}
	}
	
	private List<String> createNewTitles() {
		List<String> titles = new ArrayList<String>();
		titles.add("Lights");
		titles.add("Aqualung");
		titles.add("Soul Sister");
		titles.add("Go Now");
		titles.add("Lights");
		titles.add("Soul Sister");
		return titles;
	}
	
	private List<DownloadInfo> createExpectedDownloadList() {
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>();
		DownloadInfo di = new DownloadInfo("Hey Jude");
		di.setTimesDownloaded(5);
		downloadList.add(di);
		di = new DownloadInfo("Soul Sister");
		di.setTimesDownloaded(5);
		downloadList.add(di);
		di = new DownloadInfo("Aqualung");
		di.setTimesDownloaded(11);
		downloadList.add(di);
		di = new DownloadInfo("Lights");
		di.setTimesDownloaded(2);
		downloadList.add(di);
		di = new DownloadInfo("Go Now");
		di.setTimesDownloaded(1);
		downloadList.add(di);
		return downloadList;
	}
}
