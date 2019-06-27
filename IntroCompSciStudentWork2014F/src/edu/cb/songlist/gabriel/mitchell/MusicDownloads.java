package edu.cb.songlist.gabriel.mitchell;
import java.util.*;
import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads
{
    public MusicDownloads(){
    }
    public DownloadInfo getDownloadInfo(String title){
        for(DownloadInfo info: getDownloadList()){
            if(info.getTitle().equals(title)){
                return info;
            }
        }
        return null;
    }
    public void updateDownloads(List<String> titles){
        boolean found;
        for(String title: titles){
            found = false;
            for(DownloadInfo info: getDownloadList()){
                if(title.equals(info.getTitle())){
                    info.incrementTimesDownloaded();
                }
            }
            if(!found){
                getDownloadList().add(new DownloadInfo(title));
            }
        }
    }
}
