package edu.cb.songlist.macias.marcus;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads{
	public static void main(String[] args){
		MusicDownloads run = new MusicDownloads();
		List<DownloadInfo> downloadList = new ArrayList<>();
		downloadList.add(new DownloadInfo("Hey Jude",5));
		downloadList.add(new DownloadInfo("Soul Sister",3));
		downloadList.add(new DownloadInfo("Aqualung",10));
		List<String> list = new ArrayList<>();
		list.add("Lights");
		list.add("Aqualung");
		list.add("Soul Sister");
		list.add("Go now");
		list.add("Lights");
		list.add("Soul Sister");
		run.setDownloadList(downloadList);
		System.out.println("Download List: " + run.getDownloadList());
		System.out.println("Songs: " + list);
		run.updateDownloads(list);
		System.out.println("Updated Downloaded List: " + run.getDownloadList());
		
		
		DownloadInfo downloadInfo = run.getDownloadInfo("Lights");
		System.out.println("");
		System.out.println("Name: " +downloadInfo.getTitle());
		System.out.println("Downloads: " +downloadInfo.getTimesDownloaded());
	}
	public MusicDownloads() {
		
	}
	
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> list = getDownloadList();
		for(DownloadInfo cur : list) {
			if(cur.getTitle().equals(title)){
				return cur;
			}
		}
		return null;
	}

	
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> list = getDownloadList();
		for(String title : titles) {
			boolean addTitle = true;
			for(DownloadInfo cur : list) {
				int downloads = cur.getTimesDownloaded();
				if(title.equals(cur.getTitle())){
					cur.setTimesDownloaded(downloads + 1);
					addTitle = false;
					break;
				} 
			}
			if(addTitle) {
				getDownloadList().add(new DownloadInfo(title));
			}
		}
		
	}

}
