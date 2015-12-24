package edu.cb.songlist.burroughs.lauren;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {



	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> list = getDownloadList();
		for(int index = 0; index< list.size(); index ++){
			DownloadInfo song = list.get(index);
			if(list.get(index).getTitle().equals(title)){
				return song;
			}
			else;
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> list = getDownloadList();
		for(String title: titles){
			if(getDownloadInfo(title) != null)
				getDownloadInfo(title).incrementTimesDownloaded();
			else
				getDownloadList().add(getDownloadList().size(), getDownloadInfo(title));
		}

	}
}
