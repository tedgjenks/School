package jenks.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class PlayThread extends Thread {
  private SourceDataLine sourceDataLine;
  private AudioInputStream audioInputStream;
  private AudioFormat audioFormat;
  private boolean stopPlayback;
  
  public PlayThread(SourceDataLine sourceDataLine, AudioInputStream audioInputStream, AudioFormat audioFormat) {
	  this.sourceDataLine = sourceDataLine;
	  this.audioInputStream = audioInputStream;
	  this.audioFormat = audioFormat;
  }
	
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
      //Keep looping until the input read method
      // returns -1 for empty stream or the
      // user clicks the Stop button causing
      // stopPlayback to switch from false to
      // true.
      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length)) != -1
                       && stopPlayback == false){
        if(cnt > 0){
          //Write data to the internal buffer of
          // the data line where it will be
          // delivered to the speaker.
          sourceDataLine.write(
                             tempBuffer, 0, cnt);
        }//end if
      }//end while
      //Block and wait for internal buffer of the
      // data line to empty.
      sourceDataLine.drain();
      sourceDataLine.close();

      //Prepare to playback another file
      //stopBtn.setEnabled(false);
      //playBtn.setEnabled(true);
      stopPlayback = false;
    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }//end catch
  }//end run
}
