package edu.cb.songlist.gabriel.mitchell;
import java.util.*;
import edu.jenks.dist.cb.songlist.*;


public class MusicDownloads extends AbstractMusicDownloads
{
    public MusicDownloads(){
        super();
    }
    public DownloadInfo getDownloadInfo(String title){
        for(int i = 0; i < getDownloadList().size(); i++){
            if (getDownloadList().get(i).getTitle().equals(title)){
                return getDownloadList().get(i);
            }
        }
        return null;
    }
    public void updateDownloads(List<String> titles){
        boolean found = false;
        for (int index = 0; index < titles.size(); index++){
            for(int index2 = 0; index2 < getDownloadList().size(); index2++){
                if (getDownloadList().get(index2).getTitle().equals(titles.get(index))){
                    getDownloadList().get(index2).incrementTimesDownloaded();
                    found = true;
                }
            }
            if (!found){
                getDownloadList().add(new DownloadInfo(titles.get(index)));
            }
        }
    }
}