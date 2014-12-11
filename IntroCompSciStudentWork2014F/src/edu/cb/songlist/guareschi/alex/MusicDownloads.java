package edu.cb.songlist.guareschi.alex;
import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads{

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public DownloadInfo getDownloadInfo(String songTitle){
		List<DownloadInfo> downloadList = getDownloadList();
		for (int i = 0; i < downloadList.size(); i++){
			String songName = downloadList.get(i).getTitle();
			if (songName.equals(songTitle)){
				return downloadList.get(i);
			}
		}return null;
		}
		
	
	
	@Override
	public void updateDownloads(List<String> songTitles) {
		List<DownloadInfo> downloadFrequency = getDownloadList();
		List<DownloadInfo> downloadList = getDownloadList();
		for (int i = 0; i< downloadList.size(); i++){
			String name = downloadList.get(i).getTitle();
			if (songTitles.contains(name)){
				downloadList.get(i).incrementTimesDownloaded();
				downloadFrequency.add(downloadList.get(i));
			}else{
				downloadFrequency.add(downloadList.get(i));
			}
		}setDownloadList(downloadFrequency);
		}
	}


	