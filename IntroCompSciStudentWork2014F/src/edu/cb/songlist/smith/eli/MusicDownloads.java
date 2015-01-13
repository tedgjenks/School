package edu.cb.songlist.smith.eli;

import java.util.List;
import java.util.ArrayList;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}

	@Override
	public DownloadInfo getDownloadInfo(String songName) {
		DownloadInfo returned = null;
		List<DownloadInfo> downloadList = getDownloadList();
		for(int index = 0;returned == null && index < getDownloadList().size(); index++){
			if(songName.equals(downloadList.get(index).getTitle())) {
				returned = downloadList.get(index);
			}
		}

		return returned;
	}

	@Override
	public void updateDownloads(List<String> listOfSongs) {
		List<DownloadInfo> downloadList = getDownloadList();
		for(int index = 0; index < getDownloadList().size(); index++){
			String currentSong = listOfSongs.get(index);
			if(getDownloadInfo(currentSong) == null){
				DownloadInfo object = new DownloadInfo(currentSong);
				object.setTimesDownloaded(1);
				downloadList.add(object);

			}
			else{
				getDownloadInfo(currentSong).incrementTimesDownloaded();
			}
		}
	}

}
