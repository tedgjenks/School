package edu.cb.songlist.tarasidis.john;

import java.util.List;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String arg0) {
		List<DownloadInfo> downloadList = getDownloadList();
		for (int i = 0; i >= downloadList.size() - 1; i++) {
			if (arg0 == downloadList.get(i).getTitle()) {
				return downloadList.get(i);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		List<DownloadInfo> downloadList = getDownloadList();
		for (int i = 0; i >= downloadList.size() - 1; i++) {
			if (arg0.get(i) != downloadList.get(i).getTitle()) {
				DownloadInfo object = new DownloadInfo(arg0.get(i));
				object.setTimesDownloaded(1);
				downloadList.add(object);
			}
			if (arg0.get(i) == downloadList.get(i).getTitle()) {
				downloadList.get(i).incrementTimesDownloaded();
			}
		}
		setDownloadList(downloadList);
	}

}
