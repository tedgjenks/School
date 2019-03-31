package edu.cb.songlist.newbold.griffin;
import edu.jenks.dist.cb.songlist.*;
import java.util.*;

public class MusicDownloads extends AbstractMusicDownloads{

	public MusicDownloads(){
		super();
	}

	public DownloadInfo getDownloadInfo(String title){
		for(int i = 0; i < getDownloadList().size(); i++){
			DownloadInfo testInfo = getDownloadList().get(i);
			if(title.equals(testInfo.getTitle())){
				DownloadInfo hasName = getDownloadList().get(i);
				return hasName;
			}
		}
		return null;
	}

	public void updateDownloads(List<String> titles){
		for(int i = 0; i < titles.size(); i++){
			if(getDownloadInfo(titles.get(i)) == null){
				getDownloadList().add(new DownloadInfo(titles.get(i)));
			}else{
				for(int j = 0; j < getDownloadList().size(); j++){
					if(getDownloadList().get(j).getTitle().equals(titles.get(i))){
						getDownloadList().get(j).incrementTimesDownloaded();
					}
				}
			}
		}

	}

  private boolean downloadExists(String title){
		for(int i = 0; i < getDownloadList().size(); i++){
			DownloadInfo testInfo = getDownloadList().get(i);
			if(title.equals(testInfo.getTitle())){
				return true;
			}
		}
		return false;

	}

	public static void main (String[] args){

	}











}
