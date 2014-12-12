package edu.cb.songlist.tran.duc;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(int index = 0; index < getDownloadList().size() ; index++){
			if(title.equals(getDownloadList().get(index).getTitle())){
				return getDownloadList().get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		for(int index = 0; index < titles.size(); index++){
			String currentWord = titles.get(index);
			if(getDownloadInfo(currentWord) == null){
				getDownloadList().add(new DownloadInfo(currentWord));
				getDownloadInfo(currentWord).setTimesDownloaded(0);
			}
			getDownloadInfo(currentWord).incrementTimesDownloaded();
		}
	}

}
