package edu.cb.songlist.smith.rod;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		DownloadInfo info = new DownloadInfo("Hey Jude");
		DownloadInfo dl = new DownloadInfo("Soul Sister");
		DownloadInfo in = new DownloadInfo("Aqualung");
		return in;
		
	}

	@Override
	public void updateDownloads(List<java.lang.String> titles) {
		((AbstractMusicDownloads) getDownloadList()).getDownloadList();
	
	}

}
