package edu.cb.songlist.grenci.david;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(int index = 0; index < getDownloadList().size(); index++ ) {
			if(title.equals(getDownloadList().get(index).getTitle())){
				return getDownloadList().get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		String title;
		for(int index = 0; index < getDownloadList().size(); index++) {
			title = titles.get(index);
			if(getDownloadInfo(title) != null){
				getDownloadInfo(title).incrementTimesDownloaded();
			}
			else {
				DownloadInfo notInList = new DownloadInfo(title);
				getDownloadList().add(notInList);
				notInList.setTimesDownloaded(1);
			}
		}

	}

}
