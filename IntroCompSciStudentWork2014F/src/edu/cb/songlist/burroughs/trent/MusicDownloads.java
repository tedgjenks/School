package edu.cb.songlist.burroughs.trent;
import edu.jenks.dist.cb.songlist.*;
import java.util.*;

public class MusicDownloads extends AbstractMusicDownloads{
    
    public MusicDownloads(){
        super();
    }
    
    public DownloadInfo getDownloadInfo(String title){
        for(DownloadInfo song : getDownloadList()){
            if(song.getTitle().equals(title)){
                return song;
            }
        }
        return null;
    }
    
    public void updateDownloads(List<String> titles){
        for(int i = 0; i < titles.size(); i++){
            if(getDownloadInfo(titles.get(i)) == null){
                getDownloadList().add(new DownloadInfo(titles.get(i)));
            } else {
                getDownloadInfo(titles.get(i)).incrementTimesDownloaded();
            }
        }
    }
}
