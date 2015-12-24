package edu.cb.songlist.wicker.marshall;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for (DownloadInfo di : getDownloadList()){
			if (di.getTitle().equals(title)){
				return di;
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titleList) {
		DownloadInfo di;
		for (String title : titleList){
			di = getDownloadInfo(title);
			if (di != null){
				di.incrementTimesDownloaded();
			}
			else{
				getDownloadList().add(new DownloadInfo(title));
			}
			System.out.println("Never gonna give you up");
		}
	}

}
