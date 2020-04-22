package edu.cb.songlist.whitt.rose;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {
	
	public static void main(String[] args) {
		MusicDownloads m = new MusicDownloads();
		m.getDownloadList().add(new DownloadInfo("Hey Jude", 5));
		m.getDownloadList().add(new DownloadInfo("Soul Sister", 5));
		m.getDownloadList().add(new DownloadInfo("Aqualung", 11));
		m.getDownloadList().add(new DownloadInfo("Lights", 2));
		m.getDownloadList().add(new DownloadInfo("Go Now", 1));
		
		m.printList(m.getDownloadList());
		
		
	}
	
	public MusicDownloads() {
		
	}
	
	public DownloadInfo getDownloadInfo(String arg0) {
		List<DownloadInfo> download = getDownloadList();
		for (int i = 0; i < download.size(); i++) {
			if (download.get(i).getTitle().contentEquals(arg0)) {
				return download.get(i);
			}
		}
		return null;
	}

//	- downloadList can be accessed with getDownloadList()
//	- there are no duplicate titles in downloadList.
//	- no entries were removed from downloadList.
//	- all songs in titles are represented in downloadList.
//	- for each existing entry in downloadList, the download count is increased by
//	the number of times its title appeared in titles.
//	- the order of the existing entries in downloadList is not changed.
//	- the first time an object with a title from titles is added to downloadList, it
//	is added to the end of the list.
//	- new entries in downloadList appear in the same order
//	in which they first appear in titles.
//	- for each new entry in downloadList, the download count is equal to
//	the number of times its title appeared in titles.
	public void updateDownloads(List<String> arg0) {
		//loop through arg0
		for (int i = 0; i < arg0.size(); i++) {
			//find match
			if (getDownloadInfo(arg0.get(i)) != null) {
				getDownloadInfo(arg0.get(i)).incrementTimesDownloaded();
//				int numDown = getDownloadInfo(arg0.get(i)).getTimesDownloaded();
//				getDownloadInfo(arg0.get(i)).setTimesDownloaded(numDown + countAppearance(arg0, arg0.get(i)));
//				//remove occurences used
//				arg0.remove(arg0.get(i));
			} else {
				getDownloadList().add(new DownloadInfo(arg0.get(i), 1));
			}
		}
	}

	public int countAppearance(List<String> arg0, String title) {
		int count = 0;
		for (int i = 0; i < arg0.size(); i++) {
			if (arg0.get(i).contentEquals(title)) {
				count++;
			}
		}
		return count;
	}
	
	public void printList(List<DownloadInfo> down) {
		for (int i = 0; i < down.size(); i++) {
			System.out.println(down.get(i));
		}
	}
}
