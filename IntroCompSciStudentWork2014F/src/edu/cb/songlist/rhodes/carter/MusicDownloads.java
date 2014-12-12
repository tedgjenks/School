package edu.cb.songlist.rhodes.carter;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List <DownloadInfo> p = getDownloadList();
		for(int i = 0; i <p.size(); i ++){
			String name = p.get(i).getTitle();
			if(name.equals(title)){
				return p.get(i);
			}
	
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List <DownloadInfo> di = getDownloadList();
		for(String title:titles){
			DownloadInfo info = getDownloadInfo(title);
			if(info == null){
				di.add(new DownloadInfo(title));
				
			}else{
				info.incrementTimesDownloaded();
			}
			
		}
		
	}

}
