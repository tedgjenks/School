package edu.cb.songlist.smith.eli;

import java.util.List;
import java.util.ArrayList;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String songName) {
		int downloadIndex = 0;
		List<DownloadInfo> downloadList = getDownloadList();
		while (downloadIndex < downloadList.size()){
			if (downloadList.contains(songName) == false){
				downloadIndex++;
			}
		}
		if (downloadList.contains(downloadList.size()) == false && downloadIndex == downloadList.size()){
			return null;
		}
		else{
			return downloadList.get(downloadIndex);
		}
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		// TODO Auto-generated method stub

	}

}
