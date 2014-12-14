package edu.cb.songlist.hackett.joshua;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for (int index = 0; index < getDownloadList().size(); index++) {
			if (title.equals(getDownloadList().get(index).getTitle())) {
				return getDownloadList().get(index);
			}
			
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		// TODO Auto-generated method stub

	}

}
