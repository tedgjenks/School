package edu.cb.songlist.rhodes.maddux;

import java.util.*;
import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads{

	public MusicDownloads() {
		
	}
	
	public static void main(String[] args) {
		List<DownloadInfo> list = new ArrayList<DownloadInfo>();
		list.add(new DownloadInfo("tu madre", 2));
		list.add(new DownloadInfo("tu padre", 1));
		list.add(new DownloadInfo("tu hermano", 4));
		list.add(new DownloadInfo("tu hermana", 17));
		MusicDownloads test = new MusicDownloads();
		test.setDownloadList(list);
		test.toArray(list);
		//DownloadInfo s = test.getDownloadInfo("tu hermana");
		//list.add(s);
		//test.toArray(list);
		List<String> listTitle = new ArrayList<String>();
		listTitle.add("soul sister");
		listTitle.add("tu padre");
		listTitle.add("tu madre");
		listTitle.add("meet the monster");
		test.updateDownloads(listTitle);
		test.toArray(list);
		
		
	}
	
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> list = getDownloadList();
		for(DownloadInfo d : list) {
			if(d.getTitle().equals(title)) {
				return d;
			}
		}
		return null;
	}

	public void updateDownloads(List<String> titles) {
		for(String s : titles) {
			DownloadInfo thing = getDownloadInfo(s);
			if(thing != null) {
				thing.incrementTimesDownloaded();
			}else {
				DownloadInfo newObj = new DownloadInfo(s);
				getDownloadList().add(newObj);
			}
		}
	}

	public void toArray(List<DownloadInfo> arr) {
		System.out.print("[" + arr.get(0));
		for(int i = 1; i < arr.size(); i++) {
			System.out.print(", " + arr.get(i));
		}
		System.out.print("]");
		System.out.println();
	}
	
}
