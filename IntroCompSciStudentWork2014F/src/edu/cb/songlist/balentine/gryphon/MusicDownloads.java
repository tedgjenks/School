package edu.cb.songlist.balentine.gryphon;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads(){
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		DownloadInfo info=null;
		List<DownloadInfo> searching=getDownloadList();
		for(int index=0; index<searching.size(); index++){
			if(searching.get(index).getTitle().equals(title)){
				info=searching.get(index);
				index=searching.size()+1;
			}
		}
		return info;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> downloadList=getDownloadList();
		for(int index=0; index<titles.size()-1; index++){
			String title=titles.get(index);
			DownloadInfo info=getDownloadInfo(title);
			if(info==null){
				DownloadInfo newSong=new DownloadInfo(title);
				downloadList.add(newSong);
			} else{
				info.incrementTimesDownloaded();
			}
		}
		setDownloadList(downloadList);
	}

	public static void main(String[] args) {
	}

}
