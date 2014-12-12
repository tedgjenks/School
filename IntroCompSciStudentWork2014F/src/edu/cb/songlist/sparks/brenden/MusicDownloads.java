package edu.cb.songlist.sparks.brenden;

import java.util.List;

import edu.jenks.dist.cb.songlist.*;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List <DownloadInfo> ca = getDownloadList();
		for(int index = 0; index < ca.size (); index ++){
			String name = ca.get(index).getTitle();
			if (name.equals(title)){
				return ca.get(index);
			}
		}return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List <DownloadInfo> dl = getDownloadList();
		for (String title:titles){
			DownloadInfo songInfo = getDownloadInfo(title);
			if (songInfo == null){
				dl.add(new DownloadInfo(title));
			}else{
				songInfo.incrementTimesDownloaded();
			}
		}
	}

}
