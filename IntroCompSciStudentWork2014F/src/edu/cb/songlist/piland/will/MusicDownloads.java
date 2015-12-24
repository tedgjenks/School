package edu.cb.songlist.piland.will;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		// TODO Auto-generated method stub
		DownloadInfo check=null;
		List<DownloadInfo> downloadInfoRefreshed = getDownloadList();
		for(DownloadInfo song : downloadInfoRefreshed){
			if(song.getTitle() == title){
				check = song;
			}
		}
		return check;
	}

	@Override
	public void updateDownloads(List<String> title) {
		// TODO Auto-generated method stub
		if(title !=null){
			if(getDownloadList() != null){
				List<DownloadInfo> downloadList = getDownloadList();
				List<String> downloadInfoToAdd = title;
				for(String song: downloadInfoToAdd){
					if(getDownloadInfo(song) != null){
						getDownloadInfo(song).setTimesDownloaded(getDownloadInfo(song).getTimesDownloaded() +1);
					}
					else{
						DownloadInfo songToAdd = new DownloadInfo(song);
						songToAdd.setTimesDownloaded(1);
						downloadList.add(songToAdd);
					}

				}
			}
		}
	}


}



