package edu.cb.songlist.latham.chase;

import java.util.Iterator;
import java.util.List;
import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {	
	String object1 = (String) ("Sion1");
	String object2 = (String) ("Sion2");
	///private DownloadInfo song1;
	///DownloadInfo  downloadInfo = song1;
	
	public MusicDownloads() {
		getDownloadInfo(object1).getTitle();
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> ca = getDownloadList();
		for(int index = 0; index < ca.size(); index++){
			String name = ca.get(index).getTitle();
			if(name.equals(title));
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> d1 = getDownloadList();
		for(String title:titles){
			DownloadInfo songInfo = getDownloadInfo(title);
			if(songInfo == null){
				d1.add(new DownloadInfo(title));
			}else{
				songInfo.incrementTimesDownloaded();
			}
		}
	

	}

	public static void main(String[] args) {
		

	}

}
