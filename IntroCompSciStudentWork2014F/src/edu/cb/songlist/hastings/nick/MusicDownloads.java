package edu.cb.songlist.hastings.nick;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {
	
	public MusicDownloads(){
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		// TODO Auto-generated method stub
		for(int MusicDownloads = 0; MusicDownloads < getDownloadList().size(); MusicDownloads++ ){
			if(getDownloadList().get(MusicDownloads).getTitle().equals(title)){
				return getDownloadList().get(MusicDownloads);
				
			}
		}
		return null;
		
	}

	@Override
	public void updateDownloads(List<String> titles) {
		// TODO Auto-generated method stub
		for(int index = 0; index < titles.size(); index++ ) {
			String one = titles.get(index);
			if(getDownloadInfo(one) == null){ 
				getDownloadList().add(new DownloadInfo(one));
				getDownloadInfo(one).setTimesDownloaded(0);
			}
			getDownloadInfo(one).incrementTimesDownloaded();
		}
	}
}



