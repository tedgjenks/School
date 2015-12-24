package edu.cb.songlist.ramsey.will;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String arg0) {
		// TODO Auto-generated method stub
		for(int index = 0; index < getDownloadList().size(); index++)
			if(getDownloadList().get(index).getTitle().equals(arg0))
				return getDownloadList().get(index);	
		return null;
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		// TODO Auto-generated method stub
		for(String song:arg0)
			if(getDownloadInfo(song) == null){
				DownloadInfo newSong = new DownloadInfo(song);
				newSong.setTimesDownloaded(1);
				getDownloadList().add(newSong);
			}
			else
				getDownloadInfo(song).incrementTimesDownloaded();
	}

}
