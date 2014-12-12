package edu.cb.songlist.tarasidis.john;

import java.util.List;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String arg0) {
		for (DownloadInfo info : getDownloadList()) {
			if (info.getTitle() == arg0){
				return info;
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		for (String object : arg0) {
			DownloadInfo infoinfo = getDownloadInfo(object);
			if (infoinfo != null) {
				infoinfo.incrementTimesDownloaded();
			}
			else {
				getDownloadList().add(new DownloadInfo(object));
			}
		}
	}

}
