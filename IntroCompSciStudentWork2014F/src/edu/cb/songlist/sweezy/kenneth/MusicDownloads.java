package edu.cb.songlist.sweezy.kenneth;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		setDownloadList(new ArrayList<DownloadInfo>());
	}

	public MusicDownloads(List<DownloadInfo> info) {
		setDownloadList(info);
	}

	public DownloadInfo getDownloadInfo(String title) {
		for (DownloadInfo d : getDownloadList()) {
			if (d.getTitle().equals(title)) {
				return d;
			}
		}
		return null;
	}

	public void updateDownloads(List<String> songTitles) {
		List<DownloadInfo> temp = getDownloadList();
		List<String> titles = new ArrayList<String>();
		for (DownloadInfo m : temp) {
			titles.add(m.getTitle());
		}
		for (String s : songTitles) {
			if (titles.contains(s)) {
				for (DownloadInfo m : temp) {
					if (m.getTitle().equals(s)) {
						m.incrementTimesDownloaded();
					}
				}
			} else {
				temp.add(new DownloadInfo(s));
				titles.add(s);
			}
		}
	}

	public static void main(String[] args) {
		List<DownloadInfo> testingList = new ArrayList<DownloadInfo>();
		testingList.add(new DownloadInfo("Down with the Sickness"));
		testingList.add(new DownloadInfo("Deepthroat", 69));
		MusicDownloads testing = new MusicDownloads(testingList);
		System.out.println(testing.getDownloadInfo("Deepthroat"));
		MusicDownloads testingt = new MusicDownloads();
		System.out.println(testing.getDownloadList());
	}

}
