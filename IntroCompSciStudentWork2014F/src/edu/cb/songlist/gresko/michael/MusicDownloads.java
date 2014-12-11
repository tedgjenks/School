package edu.cb.songlist.gresko.michael;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String song) {
		DownloadInfo songInfo = null;
		List<DownloadInfo> downloadList = getDownloadList();
		for(int listIndex = 0; listIndex < downloadList.size(); listIndex++) {
			DownloadInfo temp = downloadList.get(listIndex);
			String name = temp.getTitle();
			if(name.equals(song)) {
				songInfo = temp;
			} else {
				songInfo = null;
			}
		}
		return songInfo;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> downloadList = getDownloadList();
		for(int titlesIndex = 0; titlesIndex < titles.size(); titlesIndex++) {
			for(int listIndex = 0; listIndex < downloadList.size(); listIndex++) {
				DownloadInfo temp = downloadList.get(listIndex);
				String name = temp.getTitle();
				String titlePlace = titles.get(titlesIndex);
				if(name.equals(titlePlace)) {
					temp.incrementTimesDownloaded();
				} else {
					DownloadInfo newSong = new DownloadInfo(titlePlace);
					newSong.setTimesDownloaded(1);
					downloadList.add(newSong);
				}
			}
		}
	}

}
