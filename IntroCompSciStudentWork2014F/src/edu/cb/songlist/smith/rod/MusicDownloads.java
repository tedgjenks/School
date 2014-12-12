package edu.cb.songlist.smith.rod;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}

	@Override
	public DownloadInfo getDownloadInfo(String songtitle) {
		for(int index = 0; index < getDownloadList().size(); index++) {
			if (songtitle.equals(getDownloadList().get(index).getTitle()))
				return getDownloadList().get(index);
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> musictitles) {
		String song;
		for (int index = 0; index < musictitles.size();index++){
			song = musictitles.get(index);
			if (null == getDownloadInfo(song).getTitle()){
				getDownloadList().add(new DownloadInfo(song));
				getDownloadInfo(song).setTimesDownloaded(0);
			}
			getDownloadInfo(song).incrementTimesDownloaded();
	}
	}
}
