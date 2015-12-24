package edu.cb.songlist.johnson.tatum;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(int i = 0; i < this.getDownloadList().size(); i++){
			if(getDownloadList().get(i).getTitle().compareTo(title)==0){
				DownloadInfo dI = new DownloadInfo(title);
				return dI;
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		for(int i = 0; i < titles.size()-1; i++){
			if(getDownloadInfo(titles.get(i))==null){
				DownloadInfo dI = new DownloadInfo(titles.get(i));
				getDownloadList().add(dI);	
			}
			else if (getDownloadInfo(titles.get(i))!= null){
				getDownloadList().get(i).setTimesDownloaded(getDownloadList().get(i).getTimesDownloaded()+1);
			}
		}
	}
}
