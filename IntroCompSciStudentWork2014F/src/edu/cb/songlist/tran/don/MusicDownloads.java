package edu.cb.songlist.tran.don;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {
	
	//List<DownloadInfo> w = new ArrayList<DownloadInfo>();
	
	public static void main(String[] args) {
		MusicDownloads a = new MusicDownloads();
		System.out.println(a.getDownloadInfo("pee").getTitle());
		List<DownloadInfo> whatt = new ArrayList<DownloadInfo>();
		List<String> st = new ArrayList<String>();
		st.add("pee");
		st.add("dee");
		st.add("bee");
		st.add("boop");
		st.add("doopin");
		a.updateDownloads(st);
	}
	
	public MusicDownloads() {
		/*
		w.add(new DownloadInfo("pee"));
		w.add(new DownloadInfo("dee"));
		w.add(new DownloadInfo("bee"));
		*/
	}

	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> temp = getDownloadList();
		//List<DownloadInfo> temp = w;
		for(DownloadInfo str : temp) {
			//System.out.println("ff");
			if(str.getTitle().equals(title)) {
				return str;
			}
		}
		return null;
	}

	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> temp = getDownloadList();
		//List<DownloadInfo> temp = w;
		for(String str : titles) {
			if(getDownloadInfo(str) != null) {
				getDownloadInfo(str).incrementTimesDownloaded();
			} else {
				temp.add(new DownloadInfo(str));
			}
		}
		/*
		for(DownloadInfo abs : w) {
			System.out.println(abs.getTitle() + " - " + abs.getTimesDownloaded());
		}
		*/
	}
	
}
