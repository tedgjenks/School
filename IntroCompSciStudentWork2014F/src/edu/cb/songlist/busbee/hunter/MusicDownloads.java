package edu.cb.songlist.busbee.hunter;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		DownloadInfo answer = null;
		for(int index = getDownloadList().size(); index > 0; index--){
			DownloadInfo songInfo = getDownloadList().get(index);
			if(songInfo.getTitle().equals(title)){
				answer = songInfo;
			}
		}
		return answer;
	}
	@Override
	public void updateDownloads(List<String> titles) {
		for(int index = getDownloadList().size(); index > 0; index--){
			if(!titles.contains(getDownloadList().get(index))){
				titles.add(getDownloadList().get(index).getTitle());
				getDownloadList().get(index).getTimesDownloaded();
				}
			else if(titles.contains(getDownloadList().get(index).getTitle())){
				getDownloadList().get(index).incrementTimesDownloaded();
			}
		}
	}
}
