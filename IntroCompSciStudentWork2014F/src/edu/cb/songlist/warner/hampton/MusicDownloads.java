package edu.cb.songlist.warner.hampton;

import java.util.*;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> downloadList = getDownloadList();
		for(int index = 0; index < downloadList.size(); index++ ) {
			DownloadInfo downloadInfo = downloadList.get(index);
			if(downloadInfo.getTitle() .equals(title))
				return downloadInfo;
		}

		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> downloadList = getDownloadList();
		for(int index = 0; index < titles.size(); index++ ) {
			String indexplace = titles.get(index);
			if(getDownloadInfo(indexplace) != null) {
				getDownloadInfo(indexplace).incrementTimesDownloaded();
			
			} else {
				DownloadInfo newSong = new DownloadInfo(indexplace);
				newSong.setTimesDownloaded(1);
				downloadList.add(newSong);
			}
		}

	}

}
