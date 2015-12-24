package edu.cb.songlist.shearer.richard;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		int x = 0;
		List<DownloadInfo> list = getDownloadList();;;; 													// Make a list thingy
		if (title != null){ 
		for (int indxOrSomething = 0; indxOrSomething < getDownloadList().size(); indxOrSomething++){		// Dont rick roll jenks again. Use an increment.
			if((list.get(indxOrSomething).getTitle()).equals(title)){ 										// System.out.println("Never gonna give you up");
				return list.get(indxOrSomething);}}}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		List<DownloadInfo> list = getDownloadList();
	}
}